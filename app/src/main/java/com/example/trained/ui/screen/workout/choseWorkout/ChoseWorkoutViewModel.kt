package com.example.trained.ui.screen.workout.choseWorkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trained.data.model.WorkoutModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChoseWorkoutViewModel @Inject constructor(
    private val repository: TrainedRepository
): ViewModel() {

    private var _workout: MutableLiveData<List<WorkoutModel>>? = MutableLiveData()
    val workout get() = _workout

    fun readWorkout() {
        viewModelScope.launch {
            _workout?.value = repository.readWorkoutTable()
        }
    }
}