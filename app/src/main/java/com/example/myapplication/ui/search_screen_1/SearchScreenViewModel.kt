package com.example.myapplication.ui.search_screen_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.GenresRepository
import com.example.myapplication.database.entities.GenresEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class SearchScreenViewModel(
    private val genresRepository: GenresRepository
):ViewModel() {
    val genres = MutableStateFlow<List<GenresEntity>>(emptyList())

    init {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }) {
            withContext(Dispatchers.IO){
                genresRepository.getGenresList()
            }
        }

        genresRepository.flowGenres().flowOn(Dispatchers.IO)
            .mapLatest {
                genres.value = it
            }
            .launchIn(viewModelScope)
    }
}