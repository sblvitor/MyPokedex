package com.lira.mypokedex.data.services

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon/")
    suspend fun listPokemon(@Query("offset") offset: Int): PokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(@Path("name") pokemonName: String): Pokemon

}