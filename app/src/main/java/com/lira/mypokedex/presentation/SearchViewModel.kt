package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.domain.GetPokemonByNameUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(private val getPokemonByNameUseCase: GetPokemonByNameUseCase) : ViewModel() {

    private val _searchedPokemon = MutableLiveData<State>()
    val searchedPokemon: LiveData<State> = _searchedPokemon

    fun searchPokemonByName(name: String) {
        viewModelScope.launch {
            getPokemonByNameUseCase(name)
                .onStart {
                    _searchedPokemon.postValue(State.Loading)
                }.catch {
                    _searchedPokemon.postValue(State.Error(it))
                }.collect {
                    val searchPokemonList = listOf(it)
                    _searchedPokemon.postValue(State.Success(searchPokemonList))
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Error(val error: Throwable): State()
        data class Success(val pokemonList: List<Pokemon>): State()
    }

}