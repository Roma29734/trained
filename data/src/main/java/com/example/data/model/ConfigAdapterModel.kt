package com.example.data.model

import com.example.domain.model.WorkoutDayDomainModel

data class ConfigAdapterModel(
    val id: Int,
    val day: String,
    val workout: MutableList<WorkoutDayDomainModel>,
    var openState: Boolean,
)