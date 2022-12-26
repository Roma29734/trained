package com.example.data.local.dao

import androidx.room.*
import com.example.data.model.WorkoutEntity

@Dao
interface WorkoutDao {

    @Insert(entity = WorkoutEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(day: WorkoutEntity): Long

    @Update(entity = WorkoutEntity::class)
    suspend fun updateWorkout(day: WorkoutEntity)

    @Query("SELECT * FROM workout_base")
    suspend fun readWorkoutTable(): List<WorkoutEntity>

    @Query("SELECT COUNT(*) FROM workout_base")
    suspend fun getSizeWorkoutTable(): Int
}