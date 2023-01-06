package com.example.trained.ui.screen.workout.finishedWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.DailyWorkoutModel
import com.example.data.model.TransitDailyWorkoutModel
import com.example.data.model.WorkoutTransitionModel
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.DailyWorkoutDomainModel
import com.example.domain.userCase.DayWorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FinishedViewModel @Inject constructor(
    private val dayWorkoutInteractor: DayWorkoutInteractor
): ViewModel() {

    fun updateDayWorkout(workoutTransitionModel: WorkoutTransitionModel) {
        viewModelScope.launch (Dispatchers.IO) {
            val dayWorkout = dayWorkoutInteractor.readDayWorkout()
            val completeWorkout = DailyWorkoutDomainModel(
                nameWorkout = workoutTransitionModel.workoutModel.nameWorkout,
                sumApproach = workoutTransitionModel.workoutModel.sumApproach,
                completedApproach = workoutTransitionModel.workoutModel.completedApproach,
                receptions = workoutTransitionModel.workoutModel.receptions
            )
            dayWorkout!!.workout[workoutTransitionModel.workoutModel.id] = completeWorkout
            dayWorkout.timeWorkout = workoutTransitionModel.timeWorkout
            dayWorkoutInteractor.updateDayWorkout(dayWorkout)
        }
    }
}