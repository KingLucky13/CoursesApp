package com.example.coursesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.domain.CourseDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MainState(
    val search: String = "",
    val courses: List<CourseDomain> = emptyList()
)

class MainViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    private val _stateFlow: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                courseRepository.getAllCourses()
            }.onSuccess { coursesResponse ->
                _stateFlow.update { state ->
                    state.copy(
                        courses = coursesResponse.getOrNull() ?: emptyList()
                    )
                }
            }
        }
    }

    fun onBookmark(courseId: Int) {
        viewModelScope.launch {
            val updatedCourses = _stateFlow.value.courses.map { course ->
                if (courseId == course.id) {
                    course.copy(hasLike = !course.hasLike)
                } else {
                    course
                }
            }

            _stateFlow.update { state ->
                state.copy(courses = updatedCourses)
            }

            val hasBookmark = _stateFlow.value.courses.find { it.id == courseId }!!.hasLike
            courseRepository.updateBookmark(courseId, hasBookmark)
        }

    }

    fun onSort() {
        _stateFlow.update { state ->
            state.copy(
                courses = state.courses.sortedByDescending { course ->
                    course.publishDate
                }
            )
        }
    }

    fun onSearchChanged(searchString: String){
        _stateFlow.update { state ->
            state.copy(
                search = searchString
            )
        }
    }
}