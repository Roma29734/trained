package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitDayWorkoutModel (
    val id: Int,
    val idWorkout: Int,
    val nameWorkout: String,
    val sumApproach: Int,
    val completedApproach: Int,
    val receptions: Int,
    val timeWorkout: Long,
): Parcelable