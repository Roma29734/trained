package com.example.trained.ui.screen.mainApp.addWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.toDailyNew
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.WorkoutDayDomainModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DailyStatisticsInteractor
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.Utils.getDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class AddWorkoutViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor,
    private val dailyStatisticsInteractor: DailyStatisticsInteractor,
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
            val day = getDate().dayOfWeek.toString()
            if(workoutModel.day == day) {
                val dailyModel = dailyStatisticsInteractor.readDayWorkout()
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
                updateDailyModel?.let { dailyStatisticsInteractor.updateDayWorkout(it) }
            }
        }
    }
}