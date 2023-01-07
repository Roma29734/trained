package com.example.trained.ui.screen.firstStartApp.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.WorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor,
) : ViewModel() {

    fun startSettingWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            if (workoutInteractor.getSizeWorkoutTable() != 0) return@launch
//            MONDAY TUESDAY WEDNESDAY THURSDAY SATURDAY SUNDAY
            val nameWeeks =
                listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")

            Log.d("startViewModel",nameWeeks.size.toString())

            for (i in nameWeeks.indices) {
                Log.d("startViewModel","$i")
                val model = WorkoutModel(
                    id = 0,
                    day = nameWeeks[i],
                    workout = mutableListOf()
                )
                workoutInteractor.insertWorkout(model)
            }
            Log.d("startViewModel", "Созданны базовые 7 дней")
        }
    }
}