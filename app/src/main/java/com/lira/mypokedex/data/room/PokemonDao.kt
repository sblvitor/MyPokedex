package com.lira.mypokedex.data.room

import androidx.room.*
import com.lira.mypokedex.data.model.PokemonDB
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDB)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonDB>>

    @Query("SELECT * FROM pokemon WHERE id == :id")
    fun getFavoritePokemonById(id: Long): Flow<PokemonDB?>

    @Delete
    suspend fun deleteFavPokemon(pokemon: PokemonDB)
}