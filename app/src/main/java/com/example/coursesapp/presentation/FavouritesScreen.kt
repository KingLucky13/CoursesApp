package com.example.coursesapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coursesapp.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouritesScreen(viewModel: FavouritesViewModel = koinViewModel()) {
    val state by viewModel.stateFlow.collectAsState()

    Box(modifier = Modifier
        .fillMaxWidth()
        .offset(y = 40.dp)
        .padding(16.dp),
        contentAlignment = Alignment.CenterStart) {
        Text(
            text = stringResource(R.string.favourites),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    LazyColumn(
        modifier = Modifier
            .offset(x = 16.dp, y = 100.dp)
            .width(328.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state) { course ->
            CourseCard(course, { viewModel.onBookmark(course.id) })
        }
    }
}