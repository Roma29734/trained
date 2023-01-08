package com.example.trained.ui.screen.mainApp.dayConfig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.WorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayConfigViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor,
): ViewModel() {

    private val _workout: MutableLiveData<WorkoutModel> = MutableLiveData()
    val workout get() = _workout

    fun readWorkout(day: String) {
        viewModelScope.launch (Dispatchers.IO) {
            _workout.postValue(workoutInteractor.getWorkoutByWeeks(day))
        }
    }
}