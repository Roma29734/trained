package com.example.trained.domain.repository

import androidx.lifecycle.LiveData
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.SportsmanModel
import com.example.trained.data.model.WorkoutModel

interface TrainedRepository {

    suspend fun insertUser(user: SportsmanModel)

    suspend fun updateUser(user: SportsmanModel)

    suspend fun deleteUser(user: SportsmanModel)

    suspend fun readUserTable(): SportsmanModel

    suspend fun getSizeSportsmanTable(): Int

    suspend fun insertWorkout(day: WorkoutModel)

    suspend fun updateWorkout(day: WorkoutModel)

    suspend fun readWorkoutTable(): List<WorkoutModel>

    suspend fun getSizeWorkoutTable(): Int

    suspend fun insertDayWorkout(workout: DayWorkoutModel)

    suspend fun updateDayWorkout(workout: DayWorkoutModel)

    suspend fun readDayWorkout(): DayWorkoutModel
}