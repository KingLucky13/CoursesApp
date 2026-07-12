package com.example.coursesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coursesapp.presentation.LoginScreen
import com.example.coursesapp.presentation.LoginViewModel
import com.example.coursesapp.presentation.MainScreen
import com.example.coursesapp.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


 enum class Route{
    Login,Main,Favourites
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Login.toString()
    ) {
        composable(Route.Login.name) {
            LoginScreen(navController = navController)
        }

        composable(Route.Main.name) {
            MainScreen(navController = navController)
        }
    }
}