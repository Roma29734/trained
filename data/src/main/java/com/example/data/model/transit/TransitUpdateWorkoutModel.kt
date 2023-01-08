package com.example.data.model.transit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitUpdateWorkoutModel(
    val workoutModel: TransitWorkoutModel,
    val id: Int,
): Parcelable