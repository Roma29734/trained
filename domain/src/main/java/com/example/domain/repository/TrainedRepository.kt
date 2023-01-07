package com.example.domain.repository

import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel

interface TrainedRepository {

    suspend fun insertUser(user: SportsmanModel)

    suspend fun updateUser(user: SportsmanModel)

    suspend fun deleteUser(user: SportsmanModel)

    suspend fun readUserTable(): SportsmanModel?

    suspend fun getSizeSportsmanTable(): Int

    suspend fun insertWorkout(day: WorkoutModel): Long

    suspend fun updateWorkout(day: WorkoutModel)

    suspend fun readWorkoutTable(): List<WorkoutModel>

    suspend fun getSizeWorkoutTable(): Int

    suspend fun getWorkoutByWeeks(week: String): WorkoutModel

    suspend fun insertDayWorkout(workout: DailyStatisticsModel): Long

    suspend fun updateDayWorkout(workout: DailyStatisticsModel)

    suspend fun readDayWorkout(): DailyStatisticsModel?

    suspend fun getDayWorkoutById(id: Int): DailyStatisticsModel?

    suspend fun getSizeDayWorkoutTable(): Int

    suspend fun getDayWorkoutByWorkoutId(id: Int): DailyStatisticsModel?
}