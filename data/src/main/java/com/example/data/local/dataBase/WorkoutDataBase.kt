package com.example.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.converter.DailyConverter
import com.example.data.local.converter.DateTypeConverter
import com.example.data.local.converter.ListConverter
import com.example.data.local.converter.WorkoutConverter
import com.example.data.local.dao.WorkoutDao
import com.example.data.model.DailyStatisticsEntity
import com.example.data.model.SportsmanEntity
import com.example.data.model.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class,
        SportsmanEntity::class,
        DailyStatisticsEntity::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(DateTypeConverter::class ,ListConverter::class)
@TypeConverters(WorkoutConverter::class, DailyConverter::class)
abstract class WorkoutDataBase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}