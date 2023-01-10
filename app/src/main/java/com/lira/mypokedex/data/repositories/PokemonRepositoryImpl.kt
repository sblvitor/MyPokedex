package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.data.services.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PokemonRepositoryImpl(private val service: PokemonService): PokemonRepository {

    override suspend fun listPokemon() = flow {
        val pokemonList = service.listPokemon()
        emit(pokemonList)
    }
}