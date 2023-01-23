package com.lira.mypokedex.data.services

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/")
    suspend fun listPokemon(): PokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(@Path("name") pokemonName: String): Pokemon

}