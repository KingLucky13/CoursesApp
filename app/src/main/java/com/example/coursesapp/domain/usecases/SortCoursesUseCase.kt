package com.example.coursesapp.domain.usecases

import com.example.coursesapp.domain.CourseDomain

class SortCoursesUseCase {
    operator fun invoke(courses: List<CourseDomain>): List<CourseDomain> {
        return courses.sortedByDescending { it.publishDate }
    }
}