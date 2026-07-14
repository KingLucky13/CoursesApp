package com.example.coursesapp.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): List<CourseEntity>

    @Query("SELECT * FROM courses WHERE hasLike = 1")
    suspend fun getFavouriteCourses(): List<CourseEntity>

    @Query("UPDATE courses SET hasLike = :hasLike WHERE id = :courseId")
    suspend fun updateBookmark(courseId: Int, hasLike: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCourses(courses: List<CourseEntity>)
}