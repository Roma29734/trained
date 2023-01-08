package com.example.trained.ui.screen.mainApp.updateConfig

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.transit.TransitWorkoutModel
import com.example.data.toDailyNew
import com.example.data.toDomain
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.Utils.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateConfigViewModel @Inject constructor(
    private val dayWorkoutInteractor: DayWorkoutInteractor,
    private val workoutInteractor: WorkoutInteractor,
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateWorkout(
        name: String,
        repetitions: String,
        approaches: String,
        id: Int,
        workoutModel: TransitWorkoutModel,
    ) {
        viewModelScope.launch (Dispatchers.IO) {
            val newWorkoutItem = workoutModel.workout[id]
            newWorkoutItem.repetitions = repetitions.toInt()
            newWorkoutItem.approaches = approaches.toInt()
            newWorkoutItem.nameExercise = name

            val newWorkout = workoutModel.workout
            newWorkout.add(id, newWorkoutItem)

            val newWorkoutModel = TransitWorkoutModel(
                id = workoutModel.id,
                workout = newWorkout,
                day = workoutModel.day
            )
            workoutInteractor.updateWorkout(newWorkoutModel.toDomain())

            val day = getDate().dayOfWeek.toString()
            if(workoutModel.day == day) {
                val dailyWorkout = dayWorkoutInteractor.readDayWorkout()

                dailyWorkout?.workout?.add(id, newWorkoutItem.toDomain().toDailyNew())

                dailyWorkout?.let { dayWorkoutInteractor.updateDayWorkout(it) }
            }

        }
    }
}