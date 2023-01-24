package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.PokemonResponse
import com.lira.mypokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetListOfPokemonUseCase(private val repository: PokemonRepository): UseCase<Int, PokemonResponse>() {

    override suspend fun execute(param: Int): Flow<PokemonResponse> {

        return repository.listPokemon(param)

    }
}