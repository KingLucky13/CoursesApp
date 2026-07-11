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

interface Navigate {
    fun navigateToMain()
    fun navigateToFavourites()
}

 enum class Route{
    Login,Main,Favourites
}

class AppNavigator(private val navController: NavController) : Navigate {
    override fun navigateToMain() {
        navController.navigate(Route.Main.toString())
    }

    override fun navigateToFavourites() {
        TODO("Not yet implemented")
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val appNavigator = remember { AppNavigator(navController) }

    NavHost(
        navController = navController,
        startDestination = Route.Login.toString()
    ) {
        composable(Route.Login.toString()) {
            val viewModel: LoginViewModel = koinViewModel(
                parameters = { parametersOf(appNavigator) }
            )
            LoginScreen(viewModel = viewModel)
        }

        composable(Route.Main.toString()) {
            val viewModel: MainViewModel = koinViewModel(
                parameters = { parametersOf(appNavigator) }
            )
            MainScreen(viewModel = viewModel)
        }
    }
}