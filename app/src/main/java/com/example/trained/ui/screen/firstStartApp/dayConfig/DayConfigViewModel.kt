package com.example.trained.ui.screen.firstStartApp.dayConfig

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
    private val workoutInteractor: WorkoutInteractor
): ViewModel() {

    private var _dayWorkout: MutableLiveData<WorkoutModel> = MutableLiveData()
    val dayWorkout get() = _dayWorkout

    fun getDayWorkout() {
        viewModelScope.launch (Dispatchers.IO) {
            if(workoutInteractor.getSizeWorkoutTable() != 0) {
                viewModelScope.launch(Dispatchers.Main) {
                    _dayWorkout.postValue(workoutInteractor.readWorkoutTable()[0])
                }
            }

        }
    }

}