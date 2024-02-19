package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesTable")
data class MoviesEntity(
    @PrimaryKey
    val id: String,
    val genreIds: List<Int>? = null,
    val title: String? = null,
    val posterPath: String? = null,
    var overview: String? = null
)
