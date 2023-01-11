package com.lira.mypokedex.data.model

data class Pokemon (
    val height: Long,
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeElement>,
    val weight: Long
)

data class Sprites (
    val frontDefault: String,
    val versions: Versions
)

data class Versions (
    val generationV: GenerationV
)

data class GenerationV (
    val blackWhite: BlackWhite
)

data class BlackWhite (
    val animated: Animated
)

data class Animated (
    val frontDefault: String
)

data class TypeElement (
    val type: TypeType
)

data class TypeType (
    val name: String
)
