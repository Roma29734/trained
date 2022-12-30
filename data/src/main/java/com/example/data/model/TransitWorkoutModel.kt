package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitWorkoutModel(
    val id: Int,
    val nameExercise: String,
    val repetitions: Int,
    val approaches: Int,
): Parcelable
