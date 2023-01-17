package com.lira.mypokedex.domain

import com.lira.mypokedex.core.UseCase
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.data.room.PokemonDao
import kotlinx.coroutines.flow.Flow

class GetAllFavoritePokemonUseCase(private val pokemonDao: PokemonDao): UseCase.NoParam<List<PokemonDB>>() {

    override suspend fun execute(): Flow<List<PokemonDB>> {
        return pokemonDao.getAllPokemon()
    }
}