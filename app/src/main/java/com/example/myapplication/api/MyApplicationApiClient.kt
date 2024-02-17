package com.example.myapplication.api

import com.example.myapplication.api.models.GenresApiResponse
import com.example.myapplication.api.models.MovieApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json

class MyApplicationApiClient(
    private val httpClient: HttpClient = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }
) {
    companion object{
        const val API_KEY = "?api_key=6126d9adee5e3228309751b6b26db6cf"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val MOVIES_LIST = "${BASE_URL}movie/popular${API_KEY}"
        const val GENRES_LIST = "${BASE_URL}genre/movie/list${API_KEY}"
    }

    suspend fun getMovies(pageNumber:Int): MovieApiResponse{
        return httpClient.get {
            url(MOVIES_LIST)
            parameter("page",pageNumber)
        }
            .body<MovieApiResponse>()
    }

    suspend fun getGenres(): GenresApiResponse{
        return httpClient.get {
            url(GENRES_LIST)
        }
            .body<GenresApiResponse>()
    }

}