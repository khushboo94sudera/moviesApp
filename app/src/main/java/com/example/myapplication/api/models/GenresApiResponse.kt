package com.example.myapplication.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresApiResponse(
    @SerialName("genres")
    val genres: List<Genre> = listOf()
) {
    @Serializable
    data class Genre(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String = ""
    )
}