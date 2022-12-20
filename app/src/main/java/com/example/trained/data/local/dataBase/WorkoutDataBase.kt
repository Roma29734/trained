package com.example.trained.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trained.data.local.dao.WorkoutDao
import com.example.trained.data.model.WorkoutModel

@Database(entities = [WorkoutModel::class], version = 1, exportSchema = false)
abstract class WorkoutDataBase: RoomDatabase() {
    abstract fun workoutDao() : WorkoutDao
}