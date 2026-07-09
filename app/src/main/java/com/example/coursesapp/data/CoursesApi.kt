package com.example.coursesapp.data

import retrofit2.Response
import retrofit2.http.GET

interface CoursesApi {
    @GET("")
    suspend fun getCourses(): Response<CoursesResponse>
}


