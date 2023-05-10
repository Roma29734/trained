package com.example.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DailyWorkoutModel(
    val nameWorkout: String,
    val sumApproach: Int,
    val completedApproach: Int,
    val receptions: Int,
    var projectileWeight: Int,
): Parcelable