package com.example.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.local.converter.DailyConverter
import com.example.data.model.DailyWorkoutModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "daily_statistics_table")
data class DailyStatisticsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "day")val day: String,
    @ColumnInfo(name = "workout")
    @TypeConverters(DailyConverter::class)
    val workout: List<DailyWorkoutModel>?,
    @ColumnInfo(name = "timeWorkout") val timeWorkout: Long,
): Parcelable