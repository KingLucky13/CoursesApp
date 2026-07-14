package com.example.coursesapp.domain.usecases

import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.domain.CourseDomain

class GetFavouriteCoursesUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(): Result<List<CourseDomain>> = repository.getFavouriteCourses()
}

