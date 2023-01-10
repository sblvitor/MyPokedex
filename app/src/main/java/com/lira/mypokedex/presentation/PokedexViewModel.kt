package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.domain.ListAllPokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

class PokedexViewModel(private val listAllPokemonUseCase: ListAllPokemonUseCase) : ViewModel() {

    private val _pokemon = MutableLiveData<State>()
    val pokemon: LiveData<State> = _pokemon

    private fun getAllPokemonList(){
        viewModelScope.launch {
            listAllPokemonUseCase()
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
        data class Success(val list: Response<PokemonList>): State()
        data class Error(val error: Throwable): State()
    }

}