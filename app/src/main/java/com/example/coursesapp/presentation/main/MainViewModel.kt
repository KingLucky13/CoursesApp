package com.example.coursesapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.CourseDomain
import com.example.coursesapp.domain.usecases.GetAllCoursesUseCase
import com.example.coursesapp.domain.usecases.SortCoursesUseCase
import com.example.coursesapp.domain.usecases.UpdateBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MainState(
    val search: String = "",
    val courses: List<CourseDomain> = emptyList()
)

class MainViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase
) : ViewModel() {
    private val _stateFlow: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCoursesUseCase()
                .onSuccess { courses ->
                    _stateFlow.update { state ->
                        state.copy(
                            courses = courses
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

            val course = _stateFlow.value.courses.find { it.id == courseId }
            if (course != null) {
                updateBookmarkUseCase(course.id, !course.hasLike)
            }

        }

    }

    fun onSort() {
        _stateFlow.update { state ->
            state.copy(
                courses = sortCoursesUseCase(state.courses)
            )
        }
    }

    fun onSearchChanged(searchString: String) {
        _stateFlow.update { state ->
            state.copy(
                search = searchString
            )
        }
    }
}