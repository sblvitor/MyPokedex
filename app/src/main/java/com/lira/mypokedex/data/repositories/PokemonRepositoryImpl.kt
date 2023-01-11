package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.services.PokemonService
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(private val service: PokemonService): PokemonRepository {

    override suspend fun listPokemon(pokemonId: Int) = flow {
        val pokemon = service.listPokemon(pokemonId)
        emit(pokemon)
    }
}