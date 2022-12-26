package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "workout_base")
data class WorkoutEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nameExercise") val nameExercise: String,
    @ColumnInfo(name = "repetitions") val repetitions: Int,
    @ColumnInfo(name = "approaches") val approaches: Int,
): Parcelable