package com.example.coursesapp.domain

import com.example.coursesapp.data.CourseDTO
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

fun CourseDTO.toDomain(): CourseDomain {
    return CourseDomain(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = LocalDate.parse(startDate),
        hasLike = hasLike,
        publishDate = LocalDate.parse(publishDate)
    )
}