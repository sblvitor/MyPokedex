package com.lira.mypokedex.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.lira.mypokedex.data.repositories.PokeDBRepository
import com.lira.mypokedex.data.repositories.PokeDBRepositoryImpl
import com.lira.mypokedex.data.repositories.PokemonRepository
import com.lira.mypokedex.data.repositories.PokemonRepositoryImpl
import com.lira.mypokedex.data.room.PokemonDatabase
import com.lira.mypokedex.data.services.PokemonService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP: String = "OkHttp"

    fun load(){
        loadKoinModules(networkModules() + repositoriesModule() + daoModule())
    }

    // Criado para expor o metodo de createService
    private fun networkModules(): Module {
        return module {
            // single = Sempre a mesma instância quando for pedido
            // Instância do HttpClient pro Koin prover
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<PokemonService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module{
        return module {
            single<PokemonRepository> { PokemonRepositoryImpl(get()) }
            single<PokeDBRepository> { PokeDBRepositoryImpl(get()) }
        }
    }

    private fun daoModule(): Module {
        return module {
            single { PokemonDatabase.getInstance(androidContext()).pokemonDao() }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }

}