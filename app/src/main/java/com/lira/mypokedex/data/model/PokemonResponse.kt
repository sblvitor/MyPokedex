package com.lira.mypokedex.data.model

// Classe para receber a requisicao inicial da api
data class PokemonResponse (
    val count: Long,
    val next: String,
    val previous: String?,
    val results: List<Result>
)

data class Result (
    val name: String,
    val url: String
)
