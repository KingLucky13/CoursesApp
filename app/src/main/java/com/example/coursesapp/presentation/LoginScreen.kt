package com.example.coursesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.R
import com.example.coursesapp.ui.theme.CoursesAppTheme
import com.example.coursesapp.ui.theme.LightGrey

@Preview(showBackground = true, backgroundColor = 0xFF151515)
@Composable
fun LoginScreen() {
    CoursesAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.offset(x = 16.dp, y = 140.dp)
            )
            Inputs()
            LoginButton()
            Actions()
            Divider(
                modifier = Modifier
                    .offset(x = 16.dp, y = 502.dp)
                    .width(328.dp),
                thickness = 1.dp,
                color = colorResource(R.color.stroke)
            )
            SocialMedia()
        }
    }

}


@Composable
fun Inputs() {
    Column(
        modifier = Modifier.offset(x = 16.dp, y = 204.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .width(328.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(LightGrey),
                label = {
                    Text(
                        text = stringResource(R.string.email_example),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.alpha(0.5f)
                    )
                }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .width(328.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(LightGrey),
                label = {
                    Text(
                        text = stringResource(R.string.enter_password),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.alpha(0.5f)
                    )
                }
            )
        }

    }
}

@Composable
fun LoginButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .offset(x = 16.dp, y = 376.dp)
            .size(328.dp, 40.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary // цвет кнопки
        ),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Actions() {
    Column(
        modifier = Modifier.offset(x = 96.dp, y = 432.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = stringResource(R.string.no_account),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(R.string.registration),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
        )

    }
}

@Composable
fun SocialMedia() {
    Row(
        modifier = Modifier
            .offset(x = 16.dp, y = 534.dp)
            .width(328.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .size(156.dp, 40.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(colorResource(R.color.vk_blue)),
            onClick = {}
        ) {
            Icon(
                painterResource(R.drawable.vk),
                contentDescription = "VK",
                modifier = Modifier.size(26.dp, 16.dp),
                tint = colorResource(R.color.white)
            )
        }

        IconButton(
            modifier = Modifier
                .size(156.dp, 40.dp)
                .clip(RoundedCornerShape(30.dp))
                .background( brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(R.color.ok_light_orange),
                        colorResource(R.color.ok_dark_orange)
                    )
                )),
            onClick = {}
        ) {
            Column {
                Icon(
                    painterResource(R.drawable.ok_top),
                    contentDescription = "OK_top",
                    modifier = Modifier.size(13.65.dp, 13.43.dp),
                    tint = colorResource(R.color.white)
                )
                Icon(
                    painterResource(R.drawable.ok_bottom),
                    contentDescription = "OK_bottom",
                    modifier = Modifier.size(15.29.dp, 12.38.dp),
                    tint = colorResource(R.color.white)
                )
            }
        }
    }
}