package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesByGenres")
data class MoviesByGenresEntity(
    val movieId: String? = null,//
    val title: String? = null,
    val genresId: String? = null,//
    val posterPath: String? = null,
    @PrimaryKey
    val unique: String = "${movieId}_${genresId}",//
)