package com.lira.mypokedex.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon (
    val height: Long,
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeElement>,
    val weight: Long
): Parcelable

@Parcelize
data class Sprites (
    @SerializedName("front_default")
    val frontDefault: String,
    val versions: Versions
): Parcelable

@Parcelize
data class Versions (
    @SerializedName("generation-v")
    val generationV: GenerationV
): Parcelable

@Parcelize
data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: BlackWhite
): Parcelable

@Parcelize
data class BlackWhite (
    val animated: Animated
): Parcelable

@Parcelize
data class Animated (
    @SerializedName("front_default")
    val frontDefault: String?
): Parcelable

@Parcelize
data class TypeElement (
    val type: TypeType
): Parcelable

@Parcelize
data class TypeType (
    val name: String
): Parcelable
