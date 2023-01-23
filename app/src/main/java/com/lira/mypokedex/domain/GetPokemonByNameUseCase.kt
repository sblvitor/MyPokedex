package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonByNameUseCase(private val repository: PokemonRepository): UseCase<String, Pokemon>() {

    override suspend fun execute(param: String): Flow<Pokemon> {

        return repository.getPokemonByName(param)

    }
}