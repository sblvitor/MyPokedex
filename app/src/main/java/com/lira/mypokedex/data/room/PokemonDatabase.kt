package com.lira.mypokedex.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lira.mypokedex.data.model.PokemonDB

@Database(entities = [PokemonDB::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon-db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}