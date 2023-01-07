package com.example.data.model.transit

import android.os.Parcelable
import com.example.data.model.WorkoutDayModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitWorkoutModel(
    val id: Int,
    val day: String,
    val workout: MutableList<WorkoutDayModel>,
): Parcelable
