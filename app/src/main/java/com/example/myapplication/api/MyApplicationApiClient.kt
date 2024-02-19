package com.example.myapplication.api

import com.example.myapplication.api.models.GenresApiResponse
import com.example.myapplication.api.models.MovieApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json

class MyApplicationApiClient(
    private val httpClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
) {
    companion object {
        const val API_KEY = "6126d9adee5e3228309751b6b26db6cf"
        const val ACCESS_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MTI2ZDlhZGVlNWUzMjI4MzA5NzUxYjZiMjZkYjZjZiIsInN1YiI6IjY1YzVmYjY0ZGJjYWRlMDE3YjcwNGYzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vV4sSh8UOKe3W81OimO1iM2jw86e8TRHxU-K3X6EgBs"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val MOVIES_LIST = "${BASE_URL}movie/popular"
        const val GENRES_LIST = "${BASE_URL}genre/movie/list"
    }

    suspend fun getMovies(pageNumber: Int): MovieApiResponse {
        val response = httpClient.get {
            url(MOVIES_LIST)
            header("Authorization", "Bearer $ACCESS_TOKEN")
            parameter("page", pageNumber)
            parameter("apiKey", API_KEY)
        }

        response.bodyAsText()
        return response.body<MovieApiResponse>()
    }

    suspend fun getGenres(): GenresApiResponse {
        return httpClient.get {
            url(GENRES_LIST)
        }
            .body<GenresApiResponse>()
    }

}