package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun listPokemon(pokemonId: Int): Flow<Pokemon>

}