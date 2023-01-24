package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    // funcao sem uso por enquanto (por conta da impl do paging)
    suspend fun listPokemon(page: Int): Flow<PokemonResponse>

    suspend fun getPokemonByName(pokemonName: String): Flow<Pokemon>

}