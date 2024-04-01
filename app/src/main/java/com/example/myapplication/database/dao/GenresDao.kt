package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.entities.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genresEntity: List<GenresEntity>)

    @Query(value = "SELECT * FROM genresTable")
    fun flowGenres(): Flow<List<GenresEntity>>

    @Query("SELECT * FROM genresTable WHERE id = :genresId")
    suspend fun getGenresById(genresId: String): GenresEntity?
}