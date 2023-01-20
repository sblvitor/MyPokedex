package com.lira.mypokedex.data.repositories

import com.lira.mypokedex.core.RemoteException
import com.lira.mypokedex.data.services.PokemonService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PokemonRepositoryImpl(private val service: PokemonService): PokemonRepository {

    override suspend fun listPokemon(pokemonId: Int) = flow {
        try {
            val pokemon = service.listPokemon(pokemonId)
            emit(pokemon)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possível realizar a busca!")
        }
    }

    override suspend fun getPokemonByName(pokemonName: String) = flow {
        try {
            val pokemon = service.getPokemonByName(pokemonName)
            emit(pokemon)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possível realizar a busca!")
        }
    }
}