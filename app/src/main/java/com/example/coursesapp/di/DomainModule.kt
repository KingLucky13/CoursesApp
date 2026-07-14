package com.example.coursesapp.di

import com.example.coursesapp.domain.usecases.AuthorizationUseCase
import com.example.coursesapp.domain.usecases.GetAllCoursesUseCase
import com.example.coursesapp.domain.usecases.GetFavouriteCoursesUseCase
import com.example.coursesapp.domain.usecases.SortCoursesUseCase
import com.example.coursesapp.domain.usecases.UpdateBookmarkUseCase
import org.koin.dsl.module


val domainModule = module {

    single {
        AuthorizationUseCase()
    }

    single {
        GetAllCoursesUseCase(repository = get())
    }

    single {
        UpdateBookmarkUseCase(repository = get())
    }

    single {
        SortCoursesUseCase()
    }

    single {
        GetFavouriteCoursesUseCase(repository = get())
    }

}