package com.example.data.model.transit

import android.os.Parcelable
import com.example.data.model.DailyWorkoutModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitDailyStatisticsModel (
    val id: Int,
    val day: String,
    val workout: MutableList<DailyWorkoutModel>,
    val timeWorkout: Long,
): Parcelable