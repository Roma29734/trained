package com.example.trained.ui.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.toDailyNew
import com.example.data.toData
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val workoutInteractor: WorkoutInteractor,
    private val dayWorkoutInteractor: DayWorkoutInteractor
) : ViewModel() {

    private var _profile: MutableLiveData<SportsmanModel>? = MutableLiveData()
    val profileData get() = _profile

    private var _workout: MutableLiveData<List<WorkoutModel>>? = MutableLiveData()
    val workout get() = _workout

    private var _dailyStatistics: MutableLiveData<DailyStatisticsModel> = MutableLiveData()
    val dailyStatistics get() = _dailyStatistics

    fun readProfile() {
        viewModelScope.launch {
            _profile?.value = profileInteractor.readUserTable()
        }
    }

    fun readDayWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            if (workoutInteractor.getSizeWorkoutTable() == 0) {
                return@launch
            }
            if (dayWorkoutInteractor.getSizeDayWorkoutTable() != 0) {
                Log.d("checarch","зашел в есть day тренировка")
                _dailyStatistics.postValue(dayWorkoutInteractor.readDayWorkout())
            } else {
                Log.d("checarch","зашел в нету тренировки создаю новую")
                fillDayWorkout()
            }
        }
    }

    private fun fillDayWorkout() {
        viewModelScope.launch {
            val resultWorkout = workoutInteractor.readWorkoutTable()[0]

            val resultDaylyWorkout = DailyStatisticsModel(
                id = resultWorkout.id,
                day = resultWorkout.day,
                workout = resultWorkout.workout.map { it.toDailyNew() }.toMutableList(),
                timeWorkout = 0
            )
            Log.d("checarch",resultDaylyWorkout.toString())
            dayWorkoutInteractor.insertDayWorkout(resultDaylyWorkout)
            readDayWorkout()
        }
    }
}