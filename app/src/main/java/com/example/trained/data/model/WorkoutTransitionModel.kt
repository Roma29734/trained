package com.example.trained.data.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutTransitionModel(
    val id: Int,
    val nameExercise: String,
    val repetitions: Int,
    val approaches: Int,
    val timeChill: Long,
) : Parcelable