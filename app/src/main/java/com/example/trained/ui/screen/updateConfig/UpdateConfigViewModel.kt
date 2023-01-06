package com.example.trained.ui.screen.updateConfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.WorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateConfigViewModel @Inject constructor(
    private val dayWorkoutInteractor: DayWorkoutInteractor,
    private val workoutInteractor: WorkoutInteractor,
) : ViewModel() {

//    fun updateWorkout(id: Int, name: String, approaches: Int, repetitions: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val workoutModel = WorkoutModel(
//                id = id,
//                nameExercise = name,
//                approaches = approaches,
//                repetitions = repetitions
//            )
//            workoutInteractor.updateWorkout(workoutModel)
//
//            val resultDayWorkout = dayWorkoutInteractor.getDayWorkoutByWorkoutId(id)
//
//            val dailyStatisticsModel = DailyStatisticsModel(
//                id = resultDayWorkout.id,
//                idWorkout = resultDayWorkout.idWorkout,
//                nameWorkout = name,
//                sumApproach = approaches,
//                completedApproach = resultDayWorkout.completedApproach,
//                receptions = repetitions,
//                timeWorkout = resultDayWorkout.timeWorkout
//            )
//
//            dayWorkoutInteractor.updateDayWorkout(dailyStatisticsModel)
//        }
//    }
}