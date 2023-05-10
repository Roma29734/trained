package com.example.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutDayModel(
    var nameExercise: String,
    var repetitions: Int,
    var approaches: Int,
    var projectileWeight: Int,
): Parcelable