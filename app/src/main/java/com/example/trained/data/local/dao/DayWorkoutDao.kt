package com.example.trained.data.local.dao

import androidx.room.*
import com.example.trained.data.model.DayWorkoutModel

@Dao
interface DayWorkoutDao {

    @Insert(entity = DayWorkoutModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayWorkout(workout: DayWorkoutModel): Long

    @Update(entity = DayWorkoutModel::class)
    suspend fun updateDayWorkout(workout: DayWorkoutModel)

    @Query("SELECT * FROM day_workout_table")
    suspend fun readDayWorkout(): List<DayWorkoutModel>?

    @Query("SELECT * FROM day_workout_table WHERE id LIKE :id")
    suspend fun getDayWorkoutById(id: Int): DayWorkoutModel

    @Query("SELECT COUNT(*) FROM day_workout_table")
    suspend fun getSizeDayWorkoutTable(): Int
}