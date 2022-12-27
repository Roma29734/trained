package com.example.trained.ui.screen.dayConfig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.TrainedRepository
import com.example.domain.model.WorkoutModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DayConfigViewModel @Inject constructor(
    private val repository: TrainedRepository,
): ViewModel() {

    private var _dayWorkout: MutableLiveData<List<WorkoutModel>> = MutableLiveData()
    val dayWorkout get() = _dayWorkout

    fun getDayWorkout() {
        viewModelScope.launch {
            _dayWorkout.value = repository.readWorkoutTable()
        }
    }

}