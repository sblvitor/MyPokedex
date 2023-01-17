package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import kotlinx.coroutines.flow.Flow

interface PokeDBRepository {

    suspend fun getAllPokemon(): Flow<List<PokemonDB>>

    suspend fun insertPokemon(pokemon: PokemonDB)
}