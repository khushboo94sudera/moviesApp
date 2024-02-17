package com.example.myapplication.di

import com.example.myapplication.ui.menu.MenuViewModel
import com.example.myapplication.ui.search_screen.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        MenuViewModel(get())
    }
    viewModel {
        SearchScreenViewModel(get())
    }
}