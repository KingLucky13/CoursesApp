package com.example.coursesapp.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.CourseDomain
import com.example.coursesapp.domain.usecases.GetFavouriteCoursesUseCase
import com.example.coursesapp.domain.usecases.UpdateBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val getFavouriteCoursesUseCase: GetFavouriteCoursesUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<List<CourseDomain>> = MutableStateFlow(emptyList())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getFavouriteCoursesUseCase()
                .onSuccess { courses ->
                    _stateFlow.value = courses
                }
        }
    }

    fun onBookmark(courseId: Int) {
        viewModelScope.launch {
            _stateFlow.update { courses ->
                courses.map { course ->
                    if (courseId == course.id) {
                        course.copy(hasLike = false)
                    } else {
                        course
                    }
                }
            }

            _stateFlow.update { courses ->
                courses.filter { it.id != courseId }
            }

            updateBookmarkUseCase(courseId, false)
        }
    }

}