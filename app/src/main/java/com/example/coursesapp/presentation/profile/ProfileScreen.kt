package com.example.coursesapp.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coursesapp.R

@Composable
fun ProfileScreen(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .offset(y = 40.dp)
        .padding(16.dp),
        contentAlignment = Alignment.CenterStart) {
        Text(
            text = stringResource(R.string.profile),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}