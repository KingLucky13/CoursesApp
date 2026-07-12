package com.example.coursesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.AuthorizationUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginState(
    val email: String = "",
    val password: String = ""
)

sealed interface LoginEvent {
    data object NavigateToMain : LoginEvent
    data object OpenVk : LoginEvent
    data object OpenOk : LoginEvent
}


class LoginViewModel(private val authorizationUseCase: AuthorizationUseCase) : ViewModel() {
    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val stateFlow = _stateFlow.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()

    val events = _events.asSharedFlow()

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

    fun onLoginClicked() {
        viewModelScope.launch {
            if (authorizationUseCase(stateFlow.value.email, stateFlow.value.password)) {
                _events.emit(LoginEvent.NavigateToMain)
            }
        }
    }

    fun onVkClicked() {
        viewModelScope.launch {
            _events.emit(LoginEvent.OpenVk)
        }
    }

    fun onOkClicked() {
        viewModelScope.launch {
            _events.emit(LoginEvent.OpenOk)
        }
    }


}
