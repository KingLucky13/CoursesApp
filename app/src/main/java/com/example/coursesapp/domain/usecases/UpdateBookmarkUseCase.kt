package com.example.coursesapp.domain.usecases

import com.example.coursesapp.data.CourseRepository

class UpdateBookmarkUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(courseId: Int, hasBookmark: Boolean) {
        repository.updateBookmark(courseId, hasBookmark)
    }
}