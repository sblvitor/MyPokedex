package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.domain.GetFavoritePokemonByIdUseCase
import com.lira.mypokedex.domain.InsertPokemonIntoDBUseCase
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val insertPokemonIntoDBUseCase: InsertPokemonIntoDBUseCase,
                             private val getFavoritePokemonByIdUseCase: GetFavoritePokemonByIdUseCase): ViewModel() {

    private val _favPokemon = MutableLiveData<GetOrInsert>()
    val favPokemon: LiveData<GetOrInsert> = _favPokemon


    fun insertPokemon(pokemon: PokemonDB) {
        viewModelScope.launch {
            insertPokemonIntoDBUseCase(pokemon).collect {
                _favPokemon.postValue(GetOrInsert.Insert(true))
            }
        }
    }

    fun getFavoritePokemonById(id: Long) {
        viewModelScope.launch {
            getFavoritePokemonByIdUseCase(id)
                .collect {
                    _favPokemon.postValue(GetOrInsert.Get(it))
            }
        }
    }

    sealed class GetOrInsert {
        data class Get(val pokemon: PokemonDB?): GetOrInsert()
        data class Insert(val ok: Boolean): GetOrInsert()
    }

}