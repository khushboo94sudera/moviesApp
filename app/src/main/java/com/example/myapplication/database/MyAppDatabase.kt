package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.GenresDao
import com.example.myapplication.database.dao.MoviesDao
import com.example.myapplication.database.entities.GenresEntity
import com.example.myapplication.database.entities.MoviesEntity

@Database(
    entities = [MoviesEntity::class, GenresEntity::class],
    version = 1
)

abstract class MyAppDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun genresDao(): GenresDao

}