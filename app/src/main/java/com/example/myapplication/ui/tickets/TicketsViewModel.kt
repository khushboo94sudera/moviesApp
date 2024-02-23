package com.example.myapplication.ui.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.MoviesRepository
import com.example.myapplication.database.entities.MoviesEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TicketsViewModel(
    private val repository: MoviesRepository
): ViewModel() {
    val ticketsState = MutableStateFlow<List<MoviesEntity>>(emptyList())
    init {
        viewModelScope.launch(CoroutineExceptionHandler{ coroutineContext, throwable ->
            throwable.printStackTrace()
        }) {
            repository.getTicketList().flowOn(Dispatchers.IO)
                .mapLatest {
                    ticketsState.value = it
                }.launchIn(viewModelScope)
        }
    }
}