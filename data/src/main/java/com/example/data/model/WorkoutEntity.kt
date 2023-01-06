package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.local.converter.ListConverter
import com.example.data.local.converter.WorkoutConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "workout_table")
data class WorkoutEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "workout")
    @TypeConverters(WorkoutConverter::class)
    val workout: List<WorkoutDayModel>,
): Parcelable