package com.lira.mypokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val height: Long,
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeElement>,
    val weight: Long
)

data class Sprites (
    @SerializedName("front_default")
    val frontDefault: String,
    val versions: Versions
)

data class Versions (
    @SerializedName("generation-v")
    val generationV: GenerationV
)

data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)

data class BlackWhite (
    val animated: Animated
)

data class Animated (
    @SerializedName("front_default")
    val frontDefault: String
)

data class TypeElement (
    val type: TypeType
)

data class TypeType (
    val name: String
)
