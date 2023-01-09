package com.example.data.local.dao

import androidx.room.*
import com.example.data.model.entity.DailyStatisticsEntity
import com.example.data.model.entity.SportsmanEntity
import com.example.data.model.entity.WorkoutEntity


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

    @Query("SELECT * FROM WORKOUT_TABLE WHERE day LIKE :week")
    suspend fun getWorkoutByWeeks(week: String): WorkoutEntity

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
    @Insert(entity = DailyStatisticsEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayWorkout(workout: DailyStatisticsEntity): Long

    @Update(entity = DailyStatisticsEntity::class)
    suspend fun updateDayWorkout(workout: DailyStatisticsEntity)

    @Query("SELECT * FROM daily_statistics_table")
    suspend fun readDayWorkout(): DailyStatisticsEntity?

    @Query("SELECT * FROM daily_statistics_table WHERE id LIKE :id")
    suspend fun getDayWorkoutById(id: Int): DailyStatisticsEntity

    @Query("SELECT * FROM daily_statistics_table WHERE id LIKE :id")
    suspend fun getDayWorkoutByWorkoutId(id: Int): DailyStatisticsEntity

    @Query("SELECT COUNT(*) FROM daily_statistics_table")
    suspend fun getSizeDayWorkoutTable(): Int

    @Query("DELETE FROM daily_statistics_table")
    suspend fun deleteDailyStatisticsTable()
}