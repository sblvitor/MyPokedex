package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.domain.GetPokemonByNameUseCase
import com.lira.mypokedex.domain.GetTenPokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

class PokedexViewModel(private val getTenPokemonUseCase: GetTenPokemonUseCase,
                       private val getPokemonByNameUseCase: GetPokemonByNameUseCase) : ViewModel() {

    private val _pokemon = MutableLiveData<State>()
    val pokemon: LiveData<State> = _pokemon

    init {
        getTenPokemon()
    }

    fun getTenPokemon(){
        viewModelScope.launch {
            getTenPokemonUseCase()
                .onStart {
                    _pokemon.postValue(State.Loading)
                }.catch {
                    _pokemon.postValue(State.Error(it))
                }.collect {
                    _pokemon.postValue(State.Success(it))
                }
        }
    }

    fun getPokemonByName(pokemonName: String){
        viewModelScope.launch {
            getPokemonByNameUseCase(pokemonName)
                .onStart {
                    _pokemon.postValue(State.Loading)
                }.catch {
                    _pokemon.postValue(State.Error(it))
                }.collect {
                    _pokemon.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val pokemonList: List<Pokemon>): State()
        data class Error(val error: Throwable): State()
    }

}