package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.entities.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {
    @Insert
    suspend fun insertGenres(genresEntity: List<GenresEntity>)

    @Query(value = "SELECT * FROM genresTable")
    fun flowGenres(): Flow<List<GenresEntity>>
}