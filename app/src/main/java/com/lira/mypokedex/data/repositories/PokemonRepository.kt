package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun listPokemon(): Flow<PokemonResponse>

    suspend fun getPokemonByName(pokemonName: String): Flow<Pokemon>

}