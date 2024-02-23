package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.database.dao.GenresDao
import com.example.myapplication.database.dao.MoviesByGenresDao
import com.example.myapplication.database.dao.MoviesDao
import com.example.myapplication.database.dao.MyTicketsDao
import com.example.myapplication.database.entities.GenresEntity
import com.example.myapplication.database.entities.MoviesByGenresEntity
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.database.entities.MyTicketsEntity

@Database(
    entities = [MoviesEntity::class, GenresEntity::class, MoviesByGenresEntity::class, MyTicketsEntity::class],
    version = 2
)
@TypeConverters(Converters::class)

abstract class MyAppDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun genresDao(): GenresDao

    abstract fun moviesByGenresDao():MoviesByGenresDao

    abstract fun myTicketsDao():MyTicketsDao

}