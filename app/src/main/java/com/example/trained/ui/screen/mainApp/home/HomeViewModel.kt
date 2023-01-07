package com.example.trained.ui.screen.mainApp.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.HomeModel
import com.example.data.toDailyNew
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.LoadState
import com.example.trained.utils.Utils.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val workoutInteractor: WorkoutInteractor,
    private val dayWorkoutInteractor: DayWorkoutInteractor
) : ViewModel() {

    private var _dailyWorkoutState = MutableStateFlow(HomeState())
    val dailyWorkoutState get() = _dailyWorkoutState

    private suspend fun readProfile(): SportsmanModel {
        return profileInteractor.readUserTable()!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readDayWorkout() {
        try {
            _dailyWorkoutState.update { it.copy(loadState = LoadState.LOADING) }
            viewModelScope.launch(Dispatchers.IO) {

                if(profileInteractor.getSizeSportsmanTable() == 0) return@launch

                if (dayWorkoutInteractor.getSizeDayWorkoutTable() != 0) {
                    checkDateDailyStatistics()
                    Log.d("checarch", "зашел в есть day тренировка")
                    val dailyModel = dayWorkoutInteractor.readDayWorkout()
                    _dailyWorkoutState.update {
                        it.copy(
                            loadState = LoadState.SUCCESS,
                            successState = HomeModel(dailyModel!!, readProfile())
                        )
                    }
                } else {
                    Log.d("checarch", "зашел в нету тренировки создаю новую")
                    fillDayWorkout()
                }
            }
        } catch (e: Exception) {
            _dailyWorkoutState.update { it.copy(loadState = LoadState.ERROR) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fillDayWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            _dailyWorkoutState.update { it.copy(loadState = LoadState.NON_DAILY) }

            val date = getDate().dayOfWeek.toString()

            val resultWorkout = workoutInteractor.getWorkoutByWeeks(date)

            val resultDaylyWorkout = DailyStatisticsModel(
                id = resultWorkout.id,
                day = resultWorkout.day,
                workout = resultWorkout.workout.map { it.toDailyNew() }.toMutableList(),
                timeWorkout = 0
            )
            Log.d("checarch", resultDaylyWorkout.toString())
            dayWorkoutInteractor.insertDayWorkout(resultDaylyWorkout)
            readDayWorkout()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDateDailyStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val model = dayWorkoutInteractor.readDayWorkout()
            val date = getDate().dayOfWeek.toString()
            if(model!!.day != date) {
                updateDailyStatistics()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateDailyStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val date = getDate().dayOfWeek.toString()
            val resultWorkout = workoutInteractor.getWorkoutByWeeks(date)

            val resultDaylyWorkout = DailyStatisticsModel(
                id = resultWorkout.id,
                day = resultWorkout.day,
                workout = resultWorkout.workout.map { it.toDailyNew() }.toMutableList(),
                timeWorkout = 0
            )

            dayWorkoutInteractor.updateDayWorkout(resultDaylyWorkout)
        }
    }
}