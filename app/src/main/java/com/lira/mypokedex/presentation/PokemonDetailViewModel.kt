package com.lira.mypokedex.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.domain.GetAllFavoritePokemonUseCase
import com.lira.mypokedex.domain.InsertPokemonIntoDBUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val insertPokemonIntoDBUseCase: InsertPokemonIntoDBUseCase,
                             private val getAllFavoritePokemonUseCase: GetAllFavoritePokemonUseCase): ViewModel() {

    private val _favPokemonList = MutableLiveData<List<PokemonDB>>()
    val favPokemonList: LiveData<List<PokemonDB>> = _favPokemonList

    init {
        getAllFavoritePokemon()
    }

    fun insertPokemon(pokemon: PokemonDB) {
        viewModelScope.launch {
            insertPokemonIntoDBUseCase(pokemon)
        }
    }

    private fun getAllFavoritePokemon() {
        viewModelScope.launch {
            getAllFavoritePokemonUseCase()
                .collect {
                    _favPokemonList.postValue(it)
                }
        }
    }

}