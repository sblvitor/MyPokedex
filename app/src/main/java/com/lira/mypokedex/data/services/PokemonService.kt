package com.lira.mypokedex.data.services

import com.lira.mypokedex.data.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    suspend fun listPokemon(): Response<PokemonList>

}