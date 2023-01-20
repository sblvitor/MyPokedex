package com.lira.mypokedex.data.services

import com.lira.mypokedex.data.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}/")
    suspend fun listPokemon(@Path("id") pokemonId: Int): Pokemon

    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(@Path("name") pokemonName: String): Pokemon

}