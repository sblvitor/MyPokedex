package com.lira.mypokedex.domain.di

import com.lira.mypokedex.domain.*
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
            factory { DeleteFavPokemonUseCase(get()) }
            factory { GetPokemonByNameUseCase(get()) }
        }
    }

}