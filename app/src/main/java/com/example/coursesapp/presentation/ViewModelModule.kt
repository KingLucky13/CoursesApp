package com.example.coursesapp.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { appNavigator->
        MainViewModel(courseRepository = get(), appNavigator = appNavigator.get())
    }
    viewModel { appNavigator ->
        LoginViewModel(appNavigator = appNavigator.get())
    }
}