package com.example.myapplication.api

import com.example.myapplication.api.models.MovieApiResponse
import com.example.myapplication.database.dao.GenresDao
import com.example.myapplication.database.dao.MoviesByGenresDao
import com.example.myapplication.database.dao.MoviesDao
import com.example.myapplication.database.dao.MyTicketsDao
import com.example.myapplication.database.entities.GenresEntity
import com.example.myapplication.database.entities.MoviesByGenresEntity
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.database.entities.MyTicketsEntity
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
    private val myApplicationApiClient: MyApplicationApiClient = MyApplicationApiClient(),
    private val moviesDao: MoviesDao,
    private val moviesByGenresDao: MoviesByGenresDao,
    private val myTicketsDao: MyTicketsDao,
    private val genresDao: GenresDao
) {
    suspend fun getMoviesList(page: Int) {
        val response = myApplicationApiClient.getMovies(pageNumber = page)
        response.results.map {
            it.toMoviesEntity()
        }.run {
            moviesDao.insertMovies(this)
            this.map { movie ->
                movie.genreIds?.map { genre ->
                    MoviesByGenresEntity(
                        movieId = movie.id,
                        title = movie.title,
                        genresId = genre,
                        posterPath = movie.posterPath
                    )
                }?.run {
                    moviesByGenresDao.insert(this)
                }
            }

        }
    }

    fun flowMovies(): Flow<List<MoviesEntity>> {
        return moviesDao.flowMovies()
    }

    suspend fun getMovie(id: String): MoviesEntity? {
        return moviesDao.getMovie(id = id)
    }

    fun getMoviesByGenres(genresId: String): Flow<List<MoviesByGenresEntity>> {
        return moviesByGenresDao.getMoviesByGenresDao(genreId = genresId)
    }

    suspend fun getGenresById(genresId: String): GenresEntity? {
        return genresDao.getGenresById(genresId)
    }

    suspend fun insertTicket(movieId: String):Boolean {
        return myTicketsDao.insertTicket(MyTicketsEntity(movieId)) > 0
    }

    suspend fun isBought(movieId:String): Boolean {
        return myTicketsDao.getTicketDao(movieId =movieId )!=null
    }

     fun getTicketList(): Flow<List<MoviesEntity>>{
        return moviesDao.getMoviesWithTickets()
    }

}

private fun MovieApiResponse.Result.toMoviesEntity(): MoviesEntity {
    return MoviesEntity(
        id = this.id,
        title = this.title,
        genreIds = this.genreIds,
        posterPath = this.posterPath,
        overview = this.overview
    )
}
