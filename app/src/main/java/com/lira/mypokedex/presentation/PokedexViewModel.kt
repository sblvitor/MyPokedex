package com.lira.mypokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.domain.GetPokemonByNameUseCase
import com.lira.mypokedex.domain.GetListOfPokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PokedexViewModel(private val getListOfPokemonUseCase: GetListOfPokemonUseCase,
                       private val getPokemonByNameUseCase: GetPokemonByNameUseCase) : ViewModel() {

    private val _pokemon = MutableLiveData<State>()
    val pokemon: LiveData<State> = _pokemon

    init {
        getPokemonList()
    }

    fun getPokemonList(){
        viewModelScope.launch {
            getListOfPokemonUseCase()
                .onStart {
                    _pokemon.postValue(State.Loading)
                }.catch {
                    _pokemon.postValue(State.Error(it))
                }.collect { pokemonResponse ->
                    val pokedexEntries = pokemonResponse.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonList(entry.name, entry.url, imgUrl)
                    }
                    _pokemon.postValue(State.Success(pokedexEntries))
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
                    // Criacao de lista para encaixar no State.Success
                    val pokemonList = listOf(PokemonList(it.name,
                        "https://pokeapi.co/api/v2/pokemon/${it.id}/",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"))
                    _pokemon.postValue(State.Success(pokemonList))
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val pokemonList: List<PokemonList>): State()
        data class Error(val error: Throwable): State()
    }

}