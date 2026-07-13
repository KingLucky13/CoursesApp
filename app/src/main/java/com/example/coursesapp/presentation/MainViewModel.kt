package com.example.coursesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.domain.CourseDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    private val _stateFlow: MutableStateFlow<List<CourseDomain>> = MutableStateFlow(emptyList())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                courseRepository.getAllCourses()
            }.onSuccess { coursesResponse ->
                _stateFlow.value = coursesResponse.getOrNull() ?: emptyList()
            }
        }
    }

    fun onBookmark(courseId: Int) {
        viewModelScope.launch {
            _stateFlow.update { courses ->
                courses.map { course ->
                    if (courseId == course.id) {
                        course.copy(hasLike = !course.hasLike)
                    } else {
                        course
                    }
                }
            }
            val hasBookmark = _stateFlow.value.find { it.id == courseId }!!.hasLike
            courseRepository.updateBookmark(courseId, hasBookmark)
        }
    }

    fun onSort(){
        _stateFlow.update { courses ->
            courses.sortedByDescending{ course ->
                course.publishDate
            }
        }
    }
}