package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ListAllPokemonUseCase(private val repository: PokemonRepository): UseCase.NoParam<Response<PokemonList>>() {

    override suspend fun execute(): Flow<Response<PokemonList>> {
        return repository.listPokemon()
    }

}