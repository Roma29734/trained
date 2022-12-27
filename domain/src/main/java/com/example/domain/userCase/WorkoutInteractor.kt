package com.example.domain.userCase

import com.example.domain.model.WorkoutModel
import com.example.domain.repository.TrainedRepository

class WorkoutInteractor constructor(
    private val repository: TrainedRepository,
) {
    suspend fun insertWorkout(day: WorkoutModel): Long = repository.insertWorkout(day)

    suspend fun updateWorkout(day: WorkoutModel) = repository.updateWorkout(day)

    suspend fun readWorkoutTable(): List<WorkoutModel> = repository.readWorkoutTable()

    suspend fun getSizeWorkoutTable(): Int = repository.getSizeWorkoutTable()
}