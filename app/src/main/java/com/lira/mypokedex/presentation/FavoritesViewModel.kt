package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.domain.DeleteFavPokemonUseCase
import com.lira.mypokedex.domain.GetAllFavoritePokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(private val getAllFavoritePokemonUseCase: GetAllFavoritePokemonUseCase,
                         private val deleteFavPokemonUseCase: DeleteFavPokemonUseCase) : ViewModel() {

    private val _favPokemon = MutableLiveData<State>()
    val favPokemon: LiveData<State> = _favPokemon

    init {
        getAllFavoritePokemonFav()
    }

    private fun getAllFavoritePokemonFav() {
        viewModelScope.launch {
            getAllFavoritePokemonUseCase()
                .onStart {
                    _favPokemon.postValue(State.Loading)
                }
                .catch {
                    _favPokemon.postValue(State.Error(it))
                }
                .collect {
                    _favPokemon.postValue(State.Success(it))
                }

        }
    }

    fun deleteFavPokemon(pokemon: PokemonDB) {
        viewModelScope.launch {
            deleteFavPokemonUseCase(pokemon)
                .collect {
                    _favPokemon.postValue(State.JustDeleted(true))
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val pokemonList: List<PokemonDB>): State()
        data class Error(val error: Throwable): State()
        data class JustDeleted(val ok: Boolean): State()
    }

}