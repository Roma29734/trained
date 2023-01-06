package com.example.data.model

import android.os.Parcelable
import com.example.domain.model.WorkoutDayDomainModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitWorkoutModel(
    val id: Int,
    val day: String,
    val workout: MutableList<WorkoutDayModel>,
): Parcelable
