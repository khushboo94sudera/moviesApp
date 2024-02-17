package com.example.myapplication.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class MenuViewModel(
    private val moviesRepository: MoviesRepository
): ViewModel() {
    val movies = MutableStateFlow<List<MoviesEntity>>(emptyList())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                moviesRepository.getMoviesList(page = 1)
            }
        }

        moviesRepository.flowMovies().flowOn(Dispatchers.IO)
            .mapLatest {
                movies.value = it
            }
            .launchIn(viewModelScope)
    }

}