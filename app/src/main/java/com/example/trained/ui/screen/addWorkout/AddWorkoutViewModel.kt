package com.example.trained.ui.screen.addWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.toDailyNew
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.DailyWorkoutDomainModel
import com.example.domain.model.WorkoutDayDomainModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.WorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor,
    private val dayWorkoutInteractor: DayWorkoutInteractor,
) : ViewModel() {

    fun saveWorkout(
        name: String,
        repetitions: String,
        approaches: String,
        workoutModel: WorkoutModel
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            val model = WorkoutDayDomainModel(name, repetitions.toInt(), approaches.toInt())
            val newWorkout = workoutModel.workout
            newWorkout.add(model)

            val updateWorkoutModel = WorkoutModel(
                id = workoutModel.id,
                day = workoutModel.day,
                workout = newWorkout,
            )

            workoutInteractor.updateWorkout(updateWorkoutModel)
            val dailyModel = dayWorkoutInteractor.readDayWorkout()
            val newDailyWorkout = dailyModel?.workout
            newDailyWorkout?.add(model.toDailyNew())
            val updateDailyModel = newDailyWorkout?.let {
                DailyStatisticsModel(
                    id = dailyModel.id,
                    day = dailyModel.day,
                    workout = it,
                    timeWorkout = dailyModel.timeWorkout
                )
            }
            updateDailyModel?.let { dayWorkoutInteractor.updateDayWorkout(it) }
        }

    }
}