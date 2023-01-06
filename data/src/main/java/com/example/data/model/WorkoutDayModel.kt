package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutDayModel(
    val nameExercise: String,
    val repetitions: Int,
    val approaches: Int,
): Parcelable