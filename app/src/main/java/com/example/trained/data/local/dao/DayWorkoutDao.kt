package com.example.trained.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trained.data.model.DayWorkoutModel

interface DayWorkoutDao {

    @Insert(entity = DayWorkoutModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayWorkout(workout: DayWorkoutModel)

    @Update(entity = DayWorkoutModel::class)
    suspend fun updateDayWorkout(workout: DayWorkoutModel)

    @Query("SELECT * FROM day_workout_table")
    suspend fun readDayWorkout(): DayWorkoutModel
}