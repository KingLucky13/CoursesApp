package com.example.coursesapp.di

import androidx.room.Room
import com.example.coursesapp.data.bd.AppDatabase
import com.example.coursesapp.data.bd.CourseDao
import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.data.network.CoursesApi
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
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single {
        CourseRepository(coursesApi = get(), database = get())
    }

    single<CourseDao> {
        get<AppDatabase>().courseDao()
    }
}