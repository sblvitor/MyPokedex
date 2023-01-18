package com.lira.mypokedex.domain.di

import com.lira.mypokedex.domain.GetAllFavoritePokemonUseCase
import com.lira.mypokedex.domain.GetFavoritePokemonByIdUseCase
import com.lira.mypokedex.domain.GetTenPokemonUseCase
import com.lira.mypokedex.domain.InsertPokemonIntoDBUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetTenPokemonUseCase(get()) }
            factory { InsertPokemonIntoDBUseCase(get()) }
            factory { GetAllFavoritePokemonUseCase(get()) }
            factory { GetFavoritePokemonByIdUseCase(get()) }
        }
    }

}