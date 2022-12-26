package com.example.domain.model


data class DayWorkoutModel(
    val id: Int,
    val idWorkout: Int,
    val nameWorkout: String,
    val sumApproach: Int,
    val completedApproach: Int,
    val receptions: Int,
    val timeWorkout: Long,
)
