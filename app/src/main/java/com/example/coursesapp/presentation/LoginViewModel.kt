package com.example.coursesapp.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.coursesapp.AppNavigator
import com.example.coursesapp.domain.CourseDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val email: String = "",
    val password: String = ""
)

class LoginViewModel(private val appNavigator: AppNavigator) : ViewModel() {
    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val stateFlow = _stateFlow.asStateFlow()

    fun onEmailChanged(email: String) {
        _stateFlow.update { state ->
            state.copy(
                email = email
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _stateFlow.update { state ->
            state.copy(
                password = password
            )
        }
    }

    fun authorization() {
        if (!_stateFlow.value.email.isBlank() && !_stateFlow.value.password.isBlank() && Patterns.EMAIL_ADDRESS.matcher(
                _stateFlow.value.email
            ).matches()
        ) {
            appNavigator.navigateToMain()
        }
    }


}
