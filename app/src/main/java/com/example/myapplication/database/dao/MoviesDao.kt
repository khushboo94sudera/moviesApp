package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.database.entities.MyTicketsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: List<MoviesEntity>)

    @Query(value = "SELECT * FROM moviesTable")
    fun flowMovies(): Flow<List<MoviesEntity>>

    @Query(value = "SELECT * FROM moviesTable WHERE id = :id")
    suspend fun getMovie(id:String): MoviesEntity?

    @Query("SELECT moviesTable.id, moviesTable.title, moviesTable.posterPath FROM moviesTable JOIN myTicketsTable ON moviesTable.id = myTicketsTable.movieId")
     fun getMoviesWithTickets(): Flow<List<MoviesEntity>>

}