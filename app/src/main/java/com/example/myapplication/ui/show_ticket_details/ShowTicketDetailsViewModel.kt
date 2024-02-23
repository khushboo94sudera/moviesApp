package com.example.myapplication.ui.show_ticket_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowTicketDetailsViewModel(
    private val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    val movieState = MutableStateFlow(MoviesEntity(""))
    val movie_id = savedStateHandle.get<String>("ticket_id")?: ""
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getMovie(movie_id)?.let {
                    movieState.value = it
                }
            }
        }
    }
}