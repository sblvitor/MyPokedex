package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.PokemonList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokemonRepository {

    suspend fun listPokemon(): Flow<Response<PokemonList>>

}