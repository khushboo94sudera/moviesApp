package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myTicketsTable")
data class MyTicketsEntity (
    @PrimaryKey
    val movieId:String
)