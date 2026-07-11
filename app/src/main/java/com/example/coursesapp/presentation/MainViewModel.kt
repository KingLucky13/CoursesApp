package com.example.coursesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.AppNavigator
import com.example.coursesapp.data.CourseRepository
import com.example.coursesapp.domain.CourseDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val courseRepository: CourseRepository, private val appNavigator: AppNavigator): ViewModel() {
    private val _stateFlow: MutableStateFlow<List<CourseDomain>> = MutableStateFlow(emptyList())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                courseRepository.getAllCourses()
            }.onSuccess { coursesResponse->
                _stateFlow.value = coursesResponse.getOrNull() ?: emptyList()
            }
        }
    }
}