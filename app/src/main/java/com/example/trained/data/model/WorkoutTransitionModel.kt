package com.example.trained.data.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val nameExercise: String,
    val repetitions: Int,
    val approaches: Int,
    val timeChill: String,
) : Parcelable