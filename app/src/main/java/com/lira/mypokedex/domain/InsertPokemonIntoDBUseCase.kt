package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.data.room.PokemonDao
import kotlinx.coroutines.flow.flow

class InsertPokemonIntoDBUseCase(private val pokemonDao: PokemonDao): UseCase.NoSource<PokemonDB>() {

    override suspend fun execute(param: PokemonDB) = flow {
        emit(pokemonDao.insertPokemon(param))
    }

}