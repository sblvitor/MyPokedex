package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.data.room.PokemonDao
import kotlinx.coroutines.flow.Flow

class PokeDBRepositoryImpl(private val pokemonDao: PokemonDao): PokeDBRepository {

    override suspend fun getAllPokemon(): Flow<List<PokemonDB>> {
        return pokemonDao.getAllPokemon()
    }

    override suspend fun insertPokemon(pokemon: PokemonDB) {
        pokemonDao.insertPokemon(pokemon)
    }


}