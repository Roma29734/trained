package com.example.trained.data.local.dao

import androidx.room.*
import com.example.trained.data.model.WorkoutModel

@Dao
interface WorkoutDao {

    @Insert(entity = WorkoutModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(day: WorkoutModel)

    @Update(entity = WorkoutModel::class)
    suspend fun updateWorkout(day: WorkoutModel)

    @Query("SELECT * FROM workout_base")
    suspend fun readWorkoutTable(): List<WorkoutModel>

    @Query("SELECT COUNT(*) FROM workout_base")
    suspend fun getSizeWorkoutTable(): Int
}