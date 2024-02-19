package com.example.myapplication.api

import com.example.myapplication.api.models.MovieApiResponse
import com.example.myapplication.database.dao.MoviesDao
import com.example.myapplication.database.entities.MoviesEntity
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
    private val myApplicationApiClient: MyApplicationApiClient = MyApplicationApiClient(),
    private val moviesDao: MoviesDao
) {
    suspend fun getMoviesList(page: Int){
        val response = myApplicationApiClient.getMovies(pageNumber = page)
        response.results.map {
            it.toMoviesEntity()
        }.run {
            moviesDao.insertMovies(this)
        }
    }

    fun flowMovies():Flow<List<MoviesEntity>>{
        return moviesDao.flowMovies()
    }

    suspend fun getMovie(id:String):MoviesEntity?{
        return moviesDao.getMovie(id = id)
    }
}

private fun MovieApiResponse.Result.toMoviesEntity():MoviesEntity {
    return MoviesEntity(
         id = this.id,
        title = this.title,
        genreIds = this.genreIds,
        posterPath = this.posterPath,
        overview = this.overview
    )
}
