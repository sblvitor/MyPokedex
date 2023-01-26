package com.lira.mypokedex.presentation.di

import com.lira.mypokedex.presentation.FavoritesViewModel
import com.lira.mypokedex.presentation.PokedexViewModel
import com.lira.mypokedex.presentation.PokemonDetailViewModel
import com.lira.mypokedex.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { PokedexViewModel(get(), get(), get()) }
            viewModel { PokemonDetailViewModel(get(), get(), get()) }
            viewModel { FavoritesViewModel(get(), get()) }
            viewModel { SearchViewModel(get()) }
        }
    }

}