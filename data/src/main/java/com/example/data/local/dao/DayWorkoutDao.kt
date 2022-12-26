package com.example.data.local.dao

import androidx.room.*
import com.example.data.model.DayWorkoutEntity

@Dao
interface DayWorkoutDao {

    @Insert(entity = DayWorkoutEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayWorkout(workout: DayWorkoutEntity): Long

    @Update(entity = DayWorkoutEntity::class)
    suspend fun updateDayWorkout(workout: DayWorkoutEntity)

    @Query("SELECT * FROM day_workout_table")
    suspend fun readDayWorkout(): List<DayWorkoutEntity>?

    @Query("SELECT * FROM day_workout_table WHERE id LIKE :id")
    suspend fun getDayWorkoutById(id: Int): DayWorkoutEntity

    @Query("SELECT COUNT(*) FROM day_workout_table")
    suspend fun getSizeDayWorkoutTable(): Int
}