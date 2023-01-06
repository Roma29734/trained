package com.example.domain.model

data class WorkoutModel (
    val id: Int,
    val day: String,
    val workout: MutableList<WorkoutDayDomainModel>,
)


