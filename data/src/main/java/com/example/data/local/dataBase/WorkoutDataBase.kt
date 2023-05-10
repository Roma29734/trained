package com.example.data.local.dataBase

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.converter.DailyConverter
import com.example.data.local.converter.WorkoutConverter
import com.example.data.local.dao.WorkoutDao
import com.example.data.model.entity.DailyStatisticsEntity
import com.example.data.model.entity.SportsmanEntity
import com.example.data.model.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class,
        SportsmanEntity::class,
        DailyStatisticsEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(WorkoutConverter::class, DailyConverter::class)
abstract class WorkoutDataBase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}