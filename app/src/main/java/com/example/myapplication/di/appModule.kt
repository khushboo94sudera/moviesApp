package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.api.GenresRepository
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.api.MyApplicationApiClient
import com.example.myapplication.database.MyAppDatabase
import com.example.myapplication.database.dao.GenresDao
import com.example.myapplication.database.dao.MoviesByGenresDao
import com.example.myapplication.database.dao.MoviesDao
import com.example.myapplication.database.dao.MyTicketsDao
import org.koin.dsl.module

val appModule = module {
    single {
        MoviesRepository(moviesDao = get(), myApplicationApiClient = get(), moviesByGenresDao = get(), myTicketsDao = get(), genresDao = get())
    }
    single {
        GenresRepository(genresDao = get(), myApplicationApiClient = get())
    }
    single {
        MyApplicationApiClient()
    }
    single<MyAppDatabase> {
        Room.databaseBuilder(get(), MyAppDatabase::class.java, "myapp")
            .fallbackToDestructiveMigration()
            .build()
    }
    single<MoviesDao> {
        get<MyAppDatabase>().moviesDao()
    }
    single<GenresDao> {
        get<MyAppDatabase>().genresDao()
    }
    single<MoviesByGenresDao> {
        get<MyAppDatabase>().moviesByGenresDao()
    }
    single<MyTicketsDao> {
        get<MyAppDatabase>().myTicketsDao()
    }
}