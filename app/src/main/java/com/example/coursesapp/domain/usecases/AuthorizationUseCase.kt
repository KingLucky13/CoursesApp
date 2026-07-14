package com.example.coursesapp.domain.usecases

import android.util.Patterns

class AuthorizationUseCase {
    operator fun invoke(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}