package com.example.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.WorkoutDao
import com.example.data.model.WorkoutEntity

@Database(entities = [WorkoutEntity::class], version = 1, exportSchema = false)
abstract class WorkoutDataBase: RoomDatabase() {
    abstract fun workoutDao() : WorkoutDao
}