package com.example.myapplication.ui.show

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowContentViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
):ViewModel() {
    val movieState = MutableStateFlow(MoviesEntity(""))
    init {
        val movie_id = savedStateHandle.get<String>("movie_id") ?: ""
        fetchMovieDetails(movie_id)
    }

    private fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
             withContext(Dispatchers.IO){
                moviesRepository.getMovie(movieId)?.let {
                    movieState.value = it
                }
            }
        }
    }
}