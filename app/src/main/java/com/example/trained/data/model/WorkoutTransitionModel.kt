package com.example.trained.data.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val workoutModel: DayWorkoutModel,
    val timeChill: Long,
) : Parcelable