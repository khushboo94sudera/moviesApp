package com.example.myapplication.ui.show

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.database.entities.MyTicketsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowContentViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    val movieState = MutableStateFlow(MoviesEntity(""))
    val ticketState = MutableStateFlow(false)
    private val movie_id = savedStateHandle.get<String>("movie_id") ?: ""

    init {
        fetchMovieDetails(movie_id)
        isBoughtAlready()
    }

    private fun isBoughtAlready() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                ticketState.value = moviesRepository.isBought(movieId = movie_id)
            }
        }
    }

    private fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesRepository.getMovie(movieId)?.let {
                    movieState.value = it
                }
            }
        }
    }

    private fun addedToTickets(movieId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesRepository.insertTicket(movieId = movieId).let {
                    ticketState.value = it
                }
                isBoughtAlready()
            }
        }
    }

    fun buy() {
        addedToTickets(movie_id)
    }
}