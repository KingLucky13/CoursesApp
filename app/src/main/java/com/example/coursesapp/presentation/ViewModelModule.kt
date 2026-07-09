package com.example.coursesapp.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(courseRepository = get())
    }
}