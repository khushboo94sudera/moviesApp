package com.example.myapplication.api

import com.example.myapplication.api.models.GenresApiResponse
import com.example.myapplication.database.dao.GenresDao
import com.example.myapplication.database.entities.GenresEntity
import kotlinx.coroutines.flow.Flow

class GenresRepository(
    private val myApplicationApiClient: MyApplicationApiClient = MyApplicationApiClient(),
    private val genresDao: GenresDao
) {
    suspend fun getGenresList(){
        val response = myApplicationApiClient.getGenres()
        response.genres.map {
            it.toGenresEntity()
        }.run {
            genresDao.insertGenres(this)
        }

    }

    fun flowGenres(): Flow<List<GenresEntity>>{
        return genresDao.flowGenres()
    }
}

private fun GenresApiResponse.Genre.toGenresEntity():GenresEntity {
    return GenresEntity(
        id = this.id,
        name = this.name
    )
}
