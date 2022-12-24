package com.example.trained.ui.screen.workout.transition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransitionViewModel @Inject constructor(
    private val repository: TrainedRepository
): ViewModel() {

    private var _userId: MutableLiveData<Long?> = MutableLiveData()
    val userId get() = _userId

    fun createWorkout(id: Int, sumApprocach: Int, timeChill: Long) {
        viewModelScope.launch (Dispatchers.IO) {
            val model = DayWorkoutModel(
                id = 0,
                timeWorkout = 0,
                timeChill = timeChill,
                sumApproach = sumApprocach,
                completedApproach = 0,
                idWorkout = id
            )
            viewModelScope.launch(Dispatchers.Main) {
                _userId.value = repository.insertDayWorkout(model)
            }
        }
    }

    suspend fun getWorkoutById(id: Int): DayWorkoutModel {
        return repository.getDayWorkoutById(id)
    }

}