package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.flow

class GetTenPokemonUseCase(private val repository: PokemonRepository): UseCase.NoParam<List<Pokemon>>() {

    override suspend fun execute() = flow {

        val pokemonList = mutableListOf<Pokemon>()

        for(i in 1..10) {
            repository.listPokemon(i).collect {
                pokemonList.add(it)
            }
        }

        emit(pokemonList.toList())
    }

}