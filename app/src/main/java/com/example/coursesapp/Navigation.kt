package com.example.coursesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coursesapp.presentation.LoginScreen
import com.example.coursesapp.presentation.LoginViewModel
import com.example.coursesapp.presentation.MainScreen
import com.example.coursesapp.presentation.MainViewModel
import com.example.coursesapp.ui.theme.CoursesAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


enum class Route {
    Login, Main, Favourites
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Main.name
    ) {
        composable(Route.Login.name) {
            LoginScreen(navController = navController)
        }

        composable(Route.Main.name) {
            MainScreen(navController = navController)
        }
    }
}

@Composable
fun BottomBar() {
    Column {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.5.dp,
            color = colorResource(R.color.stroke)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BottomBarButton(
                stringResource(R.string.main),
                painterResource(R.drawable.house),
                true,
                {},
                modifier = Modifier.weight(1f)
            )
            BottomBarButton(
                stringResource(R.string.favourites),
                painterResource(R.drawable.bookmark),
                false,
                {},
                modifier = Modifier.weight(1f)
            )
            BottomBarButton(
                stringResource(R.string.profile),
                painterResource(R.drawable.person),
                false,
                {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BottomBarButton(
    text: String,
    painter: Painter,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val backgroundColor = if (isActive) {
        colorResource(R.color.light_grey)
    } else {
        MaterialTheme.colorScheme.primary
    }

    val tintColor = if (isActive) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.onPrimary
    }

    Box(
        modifier = modifier.padding(top = 12.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp, 32.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(backgroundColor)
                    .clickable { onClick },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painter,
                    contentDescription = text,
                    modifier = Modifier.size(24.dp),
                    tint = tintColor
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = tintColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

