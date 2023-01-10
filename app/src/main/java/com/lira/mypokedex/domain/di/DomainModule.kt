package com.lira.mypokedex.domain.di

import com.lira.mypokedex.domain.ListAllPokemonUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { ListAllPokemonUseCase(get()) }
        }
    }

}