package com.example.coursesapp.presentation.login

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coursesapp.R
import org.koin.androidx.compose.koinViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coursesapp.Route
import androidx.core.net.toUri

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), navController: NavController) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val vkUri = stringResource(R.string.vk_uri).toUri()
    val okUri = stringResource(R.string.ok_uri).toUri()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                LoginEvent.NavigateToMain -> {
                    navController.navigate(Route.Main.name) {
                        popUpTo(Route.Login.name) {
                            inclusive = true
                        }
                    }
                }

                LoginEvent.OpenVk -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        vkUri
                    )
                    context.startActivity(intent)
                }

                LoginEvent.OpenOk -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        okUri
                    )
                    context.startActivity(intent)
                }

            }
        }
    }

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
        Inputs(viewModel, state)
        LoginButton(viewModel)
        Actions()
        Divider(
            modifier = Modifier
                .offset(x = 16.dp, y = 502.dp)
                .width(328.dp),
            thickness = 1.dp,
            color = colorResource(R.color.stroke)
        )
        SocialMedia(viewModel::onVkClicked,viewModel::onOkClicked)
    }
}


@Composable
fun Inputs(viewModel: LoginViewModel, state: LoginState) {
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
            CustomTextField(
                value = state.email,
                placeHolderText = stringResource(R.string.email_example),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                visualTransformation = VisualTransformation.None,
                viewModel::onEmailChanged
            )


        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                state.password,
                stringResource(R.string.enter_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                viewModel::onPasswordChanged
            )
        }

    }
}

@Composable
fun CustomTextField(
    value: String,
    placeHolderText: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    onValueChanged: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .width(328.dp)
            .height(42.dp)
            .background(
                colorResource(R.color.light_grey),
                RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 16.dp),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart // ← вертикальный центр
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeHolderText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.alpha(0.5f)
                    )
                }
                innerTextField()
            }
        }
    )
}


@Composable
fun LoginButton(viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onLoginClicked() },
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
        Text(text = buildAnnotatedString {
            withStyle(
                SpanStyle(color = MaterialTheme.colorScheme.onPrimary)
            ) {
                append(stringResource(R.string.no_account))
            }

            append(" ")

            withStyle(
                SpanStyle(color = MaterialTheme.colorScheme.secondary)
            ) {
                append(stringResource(R.string.registration))
            }
        },
            style = MaterialTheme.typography.titleSmall)
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
        )

    }
}

@Composable
fun SocialMedia(onVkClicked: ()-> Unit, onOkClicked: ()-> Unit) {
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
            onClick = onVkClicked
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
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(R.color.ok_light_orange),
                            colorResource(R.color.ok_dark_orange)
                        )
                    )
                ),
            onClick = onOkClicked
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