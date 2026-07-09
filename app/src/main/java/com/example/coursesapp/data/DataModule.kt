package com.example.coursesapp.data

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoursesApi::class.java)
    }

    single {
        CourseRepository(coursesApi = get())
    }
}