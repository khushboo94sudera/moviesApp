package com.example.myapplication.di

import com.example.myapplication.ui.menu.MenuViewModel
import com.example.myapplication.ui.search_category_2.SearchCategoryViewModel
import com.example.myapplication.ui.search_screen_1.SearchScreenViewModel
import com.example.myapplication.ui.show.ShowContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        MenuViewModel(get())
    }
    viewModel {
        SearchScreenViewModel(get())
    }
    viewModel {
        ShowContentViewModel(get(),get())
    }
    viewModel {
        SearchCategoryViewModel(get(),get())
    }
}