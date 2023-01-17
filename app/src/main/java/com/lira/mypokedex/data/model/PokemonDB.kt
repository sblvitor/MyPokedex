package com.lira.mypokedex.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDB(
    @PrimaryKey
    val id: Long,
    val name: String,
    val img: String
)
