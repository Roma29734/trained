package com.example.trained.ui.screen.workout.choseWorkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.userCase.DailyStatisticsInteractor

import kotlinx.coroutines.launch
import javax.inject.Inject


class ChoseWorkoutViewModel @Inject constructor(
    private val dailyStatisticsInteractor: DailyStatisticsInteractor,
): ViewModel() {

    private var _workout: MutableLiveData<DailyStatisticsModel>? = MutableLiveData()
    val workout get() = _workout

    fun readWorkout() {
        viewModelScope.launch {
            _workout?.value = dailyStatisticsInteractor.readDayWorkout()
        }
    }
}