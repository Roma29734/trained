package com.example.trained.data.model

import androidx.room.Entity



data class DayWorkoutModel(
    val day: String,
    val workout: List<WorkoutModel>,
)