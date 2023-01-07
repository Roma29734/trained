package com.example.data.model.transit

import android.os.Parcelable
import com.example.data.model.transit.TransitDailyWorkoutModel

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val workoutModel: TransitDailyWorkoutModel,
    val timeChill: Long,
    val timeWorkout: Long,
) : Parcelable