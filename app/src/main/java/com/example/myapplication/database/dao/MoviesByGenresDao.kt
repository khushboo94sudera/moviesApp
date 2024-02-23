package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.entities.MoviesByGenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesByGenresDao {

    @Insert
    fun insert(movieByGenre: List<MoviesByGenresEntity>)
    @Query(value = "SELECT * FROM moviesByGenres WHERE genresId = :genreId")
    fun getMoviesByGenresDao(genreId: String): Flow<List<MoviesByGenresEntity>>


}