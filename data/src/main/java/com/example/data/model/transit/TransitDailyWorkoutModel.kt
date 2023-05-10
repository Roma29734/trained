package com.example.data.model.transit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransitDailyWorkoutModel(
    val id: Int,
    val nameWorkout: String,
    val sumApproach: Int,
    var completedApproach: Int,
    val receptions: Int,
    var projectileWeight: Int,
): Parcelable