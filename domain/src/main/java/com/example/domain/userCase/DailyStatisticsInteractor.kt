package com.example.domain.userCase

import com.example.domain.model.DailyStatisticsModel
import com.example.domain.repository.TrainedRepository

class DailyStatisticsInteractor constructor(
    private val repository: TrainedRepository
) {

    suspend fun insertDayWorkout(workout: DailyStatisticsModel): Long =
        repository.insertDayWorkout(workout)

    suspend fun updateDayWorkout(workout: DailyStatisticsModel) = repository.updateDayWorkout(workout)

    suspend fun readDayWorkout(): DailyStatisticsModel? = repository.readDayWorkout()

    suspend fun getDayWorkoutById(id: Int): DailyStatisticsModel? = repository.getDayWorkoutById(id)

    suspend fun getSizeDayWorkoutTable(): Int = repository.getSizeDayWorkoutTable()

    suspend fun getDayWorkoutByWorkoutId(id: Int): DailyStatisticsModel? =
        repository.getDayWorkoutByWorkoutId(id)

    suspend fun deleteDailyStatisticsTable() = repository.deleteDailyStatisticsTable()
}