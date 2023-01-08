package com.example.trained.ui.screen.firstStartApp.startConfig

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.Utils.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartConfigViewModel @Inject constructor(
    private val workoutInteractor: WorkoutInteractor
): ViewModel() {

    private var _dayWorkout: MutableLiveData<WorkoutModel> = MutableLiveData()
    val dayWorkout get() = _dayWorkout

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayWorkout() {
        viewModelScope.launch (Dispatchers.IO) {
            val date = getDate().dayOfWeek.toString()
            _dayWorkout.postValue(workoutInteractor.getWorkoutByWeeks(date))
        }
    }

}