package com.example.trained.ui.screen.firstStartApp.dayAddWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WorkoutDayDomainModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.WorkoutInteractor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class DayAddWorkoutViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor,
): ViewModel() {

    fun saveWorkout(
        name: String,
        repetitions: String,
        approaches: String,
        workoutModel: WorkoutModel,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val modelWorkout = WorkoutDayDomainModel(name, repetitions.toInt(), approaches.toInt())

            val newWorkout = workoutModel.workout
            newWorkout.add(modelWorkout)

            val updateWorkoutModel = WorkoutModel(
                id = workoutModel.id,
                day = workoutModel.day,
                workout = newWorkout,
            )

            workoutInteractor.updateWorkout(updateWorkoutModel)
        }

    }
}