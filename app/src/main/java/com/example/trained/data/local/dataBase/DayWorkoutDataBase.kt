package com.example.trained.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trained.data.local.dao.DayWorkoutDao
import com.example.trained.data.local.dao.WorkoutDao
import com.example.trained.data.model.DayWorkoutModel


@Database(entities = [DayWorkoutModel::class], version = 1, exportSchema = false)
abstract class DayWorkoutDataBase: RoomDatabase() {

    abstract fun dayWorkoutDao(): DayWorkoutDao
}