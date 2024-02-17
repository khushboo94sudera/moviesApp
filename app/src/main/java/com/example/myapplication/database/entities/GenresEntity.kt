package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genresTable")
data class GenresEntity (
    @PrimaryKey
    val id:String,
    val name:String? = null
)