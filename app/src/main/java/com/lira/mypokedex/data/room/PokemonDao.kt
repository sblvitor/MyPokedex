package com.lira.mypokedex.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDB)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonDB>>
}