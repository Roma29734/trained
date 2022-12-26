package com.example.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.DayWorkoutDao
import com.example.data.model.DayWorkoutEntity


@Database(entities = [DayWorkoutEntity::class], version = 1, exportSchema = false)
abstract class DayWorkoutDataBase: RoomDatabase() {

    abstract fun dayWorkoutDao(): DayWorkoutDao
}