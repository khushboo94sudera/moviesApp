package com.example.myapplication.ui.search_category_2

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesByGenresEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCategoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
):ViewModel() {
    val moviesListByIdState = MutableStateFlow<List<MoviesByGenresEntity>>(emptyList())
    val genresNameState = MutableStateFlow<String?>(null)
    init {
        val genresId = savedStateHandle.get<String>("band_id")?: ""
        val genresName = savedStateHandle.get<String>("band_name")?: ""
        genresNameState.value = genresName

        fetchMoviesListById(genresId)
    }



    private fun fetchMoviesListById(genresId: String) {
        viewModelScope.launch {
            moviesRepository.getMoviesByGenres(genresId).flowOn(Dispatchers.IO)
                .mapLatest {
                    moviesListByIdState.value = it
                }.launchIn(viewModelScope)
        }
    }
}