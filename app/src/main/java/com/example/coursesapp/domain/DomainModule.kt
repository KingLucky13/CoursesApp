package com.example.coursesapp.domain

import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.data.CoursesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domainModule = module {

    single {
        AuthorizationUseCase()
    }
}