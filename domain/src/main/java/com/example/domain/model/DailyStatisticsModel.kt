package com.example.domain.model


data class DailyStatisticsModel(
    val id: Int,
    val day: String,
    val workout: MutableList<DailyWorkoutDomainModel>,
    var timeWorkout: Long,
    var projectileWeight: Int,
)
