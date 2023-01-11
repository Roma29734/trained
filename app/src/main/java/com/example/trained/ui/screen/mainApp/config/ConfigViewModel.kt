package com.example.trained.ui.screen.mainApp.config

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ConfigAdapterModel
import com.example.data.toConfigAdapterModel
import com.example.domain.userCase.WorkoutInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


class ConfigViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor
): ViewModel() {

    private var _dayWorkout: MutableLiveData<List<ConfigAdapterModel>> = MutableLiveData()
    val dayWorkout get() = _dayWorkout

    fun getDayWorkout() {
        viewModelScope.launch {
            _dayWorkout.value = workoutInteractor.readWorkoutTable().map { it.toConfigAdapterModel() }
        }
    }
}