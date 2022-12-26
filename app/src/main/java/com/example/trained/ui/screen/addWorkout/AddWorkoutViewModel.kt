package com.example.trained.ui.screen.addWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.WorkoutModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val repository: TrainedRepository,
): ViewModel() {

    fun saveWorkout(name: String, repetitions: String, approaches:String) {
        val workoutModel = WorkoutModel(0, name, repetitions.toInt(), approaches.toInt())

        viewModelScope.launch(Dispatchers.IO) {
            val idWorkout = repository.insertWorkout(workoutModel)

            val dayWorkoutModel = DayWorkoutModel(
                id = 0,
                idWorkout = idWorkout.toInt(),
                nameWorkout = name,
                sumApproach = approaches.toInt(),
                completedApproach = 0,
                receptions = repetitions.toInt(),
                timeWorkout = 0,
            )
            repository.insertDayWorkout(dayWorkoutModel)
        }

    }
}