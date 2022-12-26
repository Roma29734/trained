package com.example.data.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val workoutModel: DayWorkoutEntity,
    val timeChill: Long,
) : Parcelable