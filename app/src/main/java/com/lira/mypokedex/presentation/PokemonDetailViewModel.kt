package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.domain.GetFavoritePokemonByIdUseCase
import com.lira.mypokedex.domain.GetPokemonByNameUseCase
import com.lira.mypokedex.domain.InsertPokemonIntoDBUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val insertPokemonIntoDBUseCase: InsertPokemonIntoDBUseCase,
                             private val getFavoritePokemonByIdUseCase: GetFavoritePokemonByIdUseCase,
                             private val getPokemonByNameUseCase: GetPokemonByNameUseCase): ViewModel() {

    private val _pokemon = MutableLiveData<Method>()
    val pokemon: LiveData<Method> = _pokemon


    fun getPokemon(name: String) {
        viewModelScope.launch {
            getPokemonByNameUseCase(name)
                .onStart {
                    _pokemon.postValue(Method.Loading)
                }.catch {
                    _pokemon.postValue(Method.Error(it))
                }
                .collect {
                _pokemon.postValue(Method.GetFromApi(it))
            }
        }
    }

    fun insertPokemon(pokemon: PokemonDB) {
        viewModelScope.launch {
            insertPokemonIntoDBUseCase(pokemon).collect {
                _pokemon.postValue(Method.Insert(true))
            }
        }
    }

    fun getFavoritePokemonById(id: Long) {
        viewModelScope.launch {
            getFavoritePokemonByIdUseCase(id)
                .collect {
                    _pokemon.postValue(Method.GetFromDB(it))
            }
        }
    }

    sealed class Method {
        object Loading: Method()
        data class Error(val error: Throwable): Method()
        data class GetFromDB(val pokemon: PokemonDB?): Method()
        data class Insert(val ok: Boolean): Method()
        data class GetFromApi(val pokemon: Pokemon): Method()
    }

}