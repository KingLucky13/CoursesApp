package com.example.coursesapp.presentation

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.R
import com.example.coursesapp.domain.CourseDomain
import com.example.coursesapp.ui.theme.CoursesAppTheme
import com.example.coursesapp.ui.theme.Glass
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.stateFlow.collectAsState()
    Search()
    Sort()
    LazyColumn(modifier = Modifier.offset(x = 16.dp,y=164.dp).fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(state) { course ->
            CourseCard(course)
        }
    }
}

@Composable
fun Search() {
    Row(
        modifier = Modifier
            .size(328.dp, 56.dp)
            .offset(x=16.dp,y=56.dp)
            .clip(RoundedCornerShape(28.dp)),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SearchBar()

        IconButton(
            modifier = Modifier
                .size(56.dp)

                .clip(
                    RoundedCornerShape(28.dp)
                )
                .background(MaterialTheme.colorScheme.primary),
            onClick = {}
        ) {
            Icon(
                painterResource(R.drawable.filter),
                contentDescription = "Filter",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .size(264.dp, 56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(MaterialTheme.colorScheme.primary),
        leadingIcon = { SearchButton() },
        label = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.alpha(0.5f)
            )
        }
    )
}

@Composable
fun SearchButton() {
    IconButton(
        modifier = Modifier
            .size(48.dp),
        onClick = {}
    ) {
        Icon(
            painterResource(R.drawable.search),
            contentDescription = "Search",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun Sort() {
    Row(
        modifier = Modifier.offset(x = 188.dp, y = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(R.string.filter_name),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        IconButton(
            modifier = Modifier
                .size(16.dp),
            onClick = {}
        ) {
            Icon(
                painterResource(R.drawable.arrow_down_up),
                contentDescription = "Arrow-down-up",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun CourseCard(course: CourseDomain) {
    Box(
        modifier = Modifier
            .size(328.dp, 236.dp)
            .offset(x = 16.dp, y = 56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary),
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Bookmark()

            Row(
                modifier = Modifier.offset(x = 8.dp, y = 84.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Info1(course)
                Info2(course)
            }
        }

        BottomSection(course)

        ButtonMore()
    }
}

@Composable
fun Bookmark() {
    IconButton(
        modifier = Modifier
            .size(28.dp)
            .offset(x = 292.dp, y = 8.dp)

            .clip(
                RoundedCornerShape(20.dp)
            )
            .padding(6.dp)
            .background(Glass),
        onClick = {}
    ) {
        Icon(
            painterResource(R.drawable.bookmark),
            contentDescription = "Bookmark",
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun Info1(course: CourseDomain) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Glass)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.star_fill),
                contentDescription = "Star",
                modifier = Modifier.size(12.dp),
                tint = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = course.rate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Composable
fun Info2(course: CourseDomain) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Glass)
    ) {
        Text(
            text = course.publishDate,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun BottomSection(course: CourseDomain) {
    Box(
        modifier = Modifier
            .size(300.dp, 90.dp)
            .offset(x = 16.dp, y = 130.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = course.text,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = course.price, style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun ButtonMore() {
    Row(
        modifier = Modifier.offset(x = 234.dp, y = 203.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.details),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Icon(
            painterResource(R.drawable.right_arrow),
            contentDescription = "RightArrow",
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}