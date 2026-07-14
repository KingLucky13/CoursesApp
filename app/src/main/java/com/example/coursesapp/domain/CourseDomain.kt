package com.example.coursesapp.domain

import com.example.coursesapp.data.network.CourseDTO
import java.time.LocalDate

data class CourseDomain(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)

