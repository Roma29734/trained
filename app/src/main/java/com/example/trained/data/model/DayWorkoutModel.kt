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
    @ColumnInfo(name = "timeWorkout") val timeWorkout: Long,
    @ColumnInfo(name = "timeChill") val timeChill: Long,
    @ColumnInfo(name = "sumApproach") val sumApproach: Int,
    @ColumnInfo(name = "completedApproach") val completedApproach: Int,
    @ColumnInfo(name = "idWorkout") val idWorkout: Int,
): Parcelable