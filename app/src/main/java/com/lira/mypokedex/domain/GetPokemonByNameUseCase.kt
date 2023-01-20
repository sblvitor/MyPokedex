package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

class GetPokemonByNameUseCase(private val repository: PokemonRepository): UseCase<String, List<Pokemon>>() {

    // API devolve só um pokemon, portanto necessario criar uma lista com um unico pokemon para fluxo seguir correto.
    override suspend fun execute(param: String) = flow {
        val list = mutableListOf<Pokemon>()
        repository.getPokemonByName(param).collect {
            list.add(it)
        }
        emit(list)
    }
}