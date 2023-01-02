package com.example.data.local.dao

import androidx.room.*
import com.example.data.model.DayWorkoutEntity
import com.example.data.model.SportsmanEntity
import com.example.data.model.WorkoutEntity

@Dao
interface WorkoutDao {


//    Workout
    @Insert(entity = WorkoutEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(day: WorkoutEntity): Long

    @Update(entity = WorkoutEntity::class)
    suspend fun updateWorkout(day: WorkoutEntity)

    @Query("SELECT * FROM workout_table")
    suspend fun readWorkoutTable(): List<WorkoutEntity>

    @Query("SELECT COUNT(*) FROM workout_table")
    suspend fun getSizeWorkoutTable(): Int

//    User
    @Insert(entity = SportsmanEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: SportsmanEntity)

    @Update(entity = SportsmanEntity::class)
    suspend fun updateUser(user: SportsmanEntity)

    @Delete(entity = SportsmanEntity::class)
    suspend fun deleteUser(user: SportsmanEntity)

    @Query("SELECT * FROM sportsman_table")
    suspend fun readUserTable(): SportsmanEntity?

    @Query("SELECT COUNT(*) FROM sportsman_table")
    suspend fun getSizeSportsmanTable(): Int

//    DayWorkout
    @Insert(entity = DayWorkoutEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayWorkout(workout: DayWorkoutEntity): Long

    @Update(entity = DayWorkoutEntity::class)
    suspend fun updateDayWorkout(workout: DayWorkoutEntity)

    @Query("SELECT * FROM day_workout_table")
    suspend fun readDayWorkout(): List<DayWorkoutEntity>?

    @Query("SELECT * FROM day_workout_table WHERE id LIKE :id")
    suspend fun getDayWorkoutById(id: Int): DayWorkoutEntity

    @Query("SELECT * FROM day_workout_table WHERE idWorkout LIKE :id")
    suspend fun getDayWorkoutByWorkoutId(id: Int): DayWorkoutEntity

    @Query("SELECT COUNT(*) FROM day_workout_table")
    suspend fun getSizeDayWorkoutTable(): Int
}