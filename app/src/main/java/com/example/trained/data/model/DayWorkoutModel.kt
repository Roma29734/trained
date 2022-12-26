package com.example.trained.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "day_workout_table")
data class DayWorkoutModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "idWorkout") val idWorkout: Int,
    @ColumnInfo(name = "nameWorkout") val nameWorkout: String,
    @ColumnInfo(name = "sumApproach") val sumApproach: Int,
    @ColumnInfo(name = "completedApproach") val completedApproach: Int,
    @ColumnInfo(name = "receptions") val receptions: Int,
    @ColumnInfo(name = "timeWorkout") val timeWorkout: Long,
): Parcelable