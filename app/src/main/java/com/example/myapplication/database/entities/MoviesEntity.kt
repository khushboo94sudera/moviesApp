package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesTable")
data class MoviesEntity(
    @PrimaryKey
    val id: String,
    val genreIds: List<String>?= null,
    val title: String? = null,
    val posterPath: String? = null,
    val overview: String? = null
)
