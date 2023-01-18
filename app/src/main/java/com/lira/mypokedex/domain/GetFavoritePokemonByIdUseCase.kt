package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.data.room.PokemonDao
import kotlinx.coroutines.flow.Flow

class GetFavoritePokemonByIdUseCase(private val pokemonDao: PokemonDao): UseCase<Long, PokemonDB?>() {

    override suspend fun execute(param: Long): Flow<PokemonDB?> {
        return pokemonDao.getFavoritePokemonById(param)
    }
}