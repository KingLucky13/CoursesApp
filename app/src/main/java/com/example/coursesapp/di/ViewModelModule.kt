package com.example.coursesapp.di

import com.example.coursesapp.presentation.favourites.FavouritesViewModel
import com.example.coursesapp.presentation.login.LoginViewModel
import com.example.coursesapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            getAllCoursesUseCase = get(),
            updateBookmarkUseCase = get(),
            sortCoursesUseCase = get()
        )
    }
    viewModel {
        FavouritesViewModel(getFavouriteCoursesUseCase = get(), updateBookmarkUseCase = get())
    }
    viewModel {
        LoginViewModel(authorizationUseCase = get())
    }
}