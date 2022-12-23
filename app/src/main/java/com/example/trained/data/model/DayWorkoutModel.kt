package com.example.trained.data.model

import androidx.room.Entity

@Entity(tableName = "day_workout_table")
data class DayWorkoutModel(
    val timeWorkout: String,
    val sumExercises: Int,
    val completedExercises: Int,
)
