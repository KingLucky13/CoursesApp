package com.example.coursesapp.data

import com.example.coursesapp.domain.CourseDomain
import com.example.coursesapp.domain.toDomain
import java.io.IOException

class CourseRepository(
    private val coursesApi: CoursesApi
) {

    suspend fun getAllCourses(): Result<List<CourseDomain>> {
        return try {
            val coursesResponse = coursesApi.getCourses()
            if (coursesResponse.isSuccessful && coursesResponse.body() != null) {
                val courses = coursesResponse.body()!!.courses.map { it.toDomain() }
                return Result.success(courses)
            } else {
                return Result.failure(IOException("Fail"))
            }
        } catch (e: Error) {
            Result.failure(IOException(e.message))
        }
    }
}

data class CoursesResponse(
    val courses: List<CourseDTO>
)
