package com.example.domain.userCase

import com.example.domain.model.DayWorkoutModel
import com.example.domain.repository.TrainedRepository

class DayWorkoutInteractor constructor(
    private val repository: TrainedRepository
) {

    suspend fun insertDayWorkout(workout: DayWorkoutModel): Long =
        repository.insertDayWorkout(workout)

    suspend fun updateDayWorkout(workout: DayWorkoutModel) = repository.updateDayWorkout(workout)

    suspend fun readDayWorkout(): List<DayWorkoutModel>? = repository.readDayWorkout()

    suspend fun getDayWorkoutById(id: Int): DayWorkoutModel = repository.getDayWorkoutById(id)

    suspend fun getSizeDayWorkoutTable(): Int = repository.getSizeWorkoutTable()
}