package com.example.coursesapp.data

import com.example.coursesapp.domain.CourseDomain
import java.io.IOException

class CourseRepository(
    private val coursesApi: CoursesApi,
    private val database: AppDatabase
) {

    suspend fun getAllCourses(): Result<List<CourseDomain>> {
        return try {
            val localCourses = database.courseDao().getAllCourses()
            if (localCourses.isNotEmpty()) {
                return Result.success(CourseMapper.toDomainListFromEntities(localCourses))
            } else {
                val coursesResponse = coursesApi.getCourses()
                if (coursesResponse.isSuccessful && coursesResponse.body() != null) {
                    val courses = CourseMapper.toDomainListFromDTO(coursesResponse.body()!!.courses)
                    database.courseDao().insertAllCourses(CourseMapper.toEntityList(courses))
                    return Result.success(courses)
                } else {
                    return Result.failure(IOException("Fail"))
                }
            }
        } catch (e: Error) {
            Result.failure(IOException(e.message))
        }
    }

    suspend fun getFavouriteCourses(): Result<List<CourseDomain>>{
        return try{
            val favouriteCourses =database.courseDao().getFavouriteCourses()
            return Result.success(CourseMapper.toDomainListFromEntities(favouriteCourses))
        } catch (e: Error) {
            Result.failure(IOException(e.message))
        }
    }

    suspend fun updateBookmark(courseId:Int,hasBookmark: Boolean){
        database.courseDao().updateBookmark(courseId,hasBookmark)
    }
}

data class CoursesResponse(
    val courses: List<CourseDTO>
)
