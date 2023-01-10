package com.lira.mypokedex.data.model

data class Info (
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<PokemonList>
)
