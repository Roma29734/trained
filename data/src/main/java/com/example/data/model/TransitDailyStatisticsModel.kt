package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitDailyStatisticsModel (
    val id: Int,
    val day: String,
    val workout: MutableList<DailyWorkoutModel>,
    val timeWorkout: Long,
): Parcelable