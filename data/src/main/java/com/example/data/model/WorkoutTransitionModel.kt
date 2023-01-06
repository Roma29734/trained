package com.example.data.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val workoutModel: TransitDailyWorkoutModel,
    val timeChill: Long,
    val timeWorkout: Long,
) : Parcelable