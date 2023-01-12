package com.example.trained.ui.screen.mainApp.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.HomeModel
import com.example.data.toDailyNew
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.SportsmanModel
import com.example.domain.userCase.DailyStatisticsInteractor
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.LoadState
import com.example.trained.utils.Utils.getDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val workoutInteractor: WorkoutInteractor,
    private val dailyStatisticsInteractor: DailyStatisticsInteractor
) : ViewModel() {

    private var _dailyWorkoutState = MutableStateFlow(HomeState())
    val dailyWorkoutState get() = _dailyWorkoutState

    private suspend fun readProfile(): SportsmanModel {
        return profileInteractor.readUserTable()!!
    }

    fun readDayWorkout() {
        try {
            _dailyWorkoutState.update { it.copy(loadState = LoadState.LOADING) }
            viewModelScope.launch(Dispatchers.IO) {

//                Проверка на наличие профиля
                if(profileInteractor.getSizeSportsmanTable() == 0) return@launch

//                Проверка на наличие таблицы дневной статистики
                if (dailyStatisticsInteractor.getSizeDayWorkoutTable() != 0) {
//                    Проверка на соответствие даты в таблице дневной статистики и текущей даты
                    checkDateDailyStatistics()
                    val dailyModel = dailyStatisticsInteractor.readDayWorkout()
                    _dailyWorkoutState.update {
                        it.copy(
                            loadState = LoadState.SUCCESS,
                            successState = HomeModel(dailyModel!!, readProfile())
                        )
                    }
                } else {
                    fillDayWorkout()
                }
            }
        } catch (e: Exception) {
            _dailyWorkoutState.update { it.copy(loadState = LoadState.ERROR) }
        }
    }

// создание таблицы дневной статистики
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
            dailyStatisticsInteractor.insertDayWorkout(resultDaylyWorkout)
            readDayWorkout()
        }
    }

//  Проверка даты в таблице дневной статистики
    private fun checkDateDailyStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val model = dailyStatisticsInteractor.readDayWorkout()
            val date = getDate().dayOfWeek.toString()

            if(model!!.day != date) {
                updateDailyStatistics(date)
            }
        }
    }

//  Обновление даты в таблице дневной статистики
    private fun updateDailyStatistics(date: String) {
        viewModelScope.launch(Dispatchers.IO) {

            dailyStatisticsInteractor.deleteDailyStatisticsTable()
            val resultWorkout = workoutInteractor.getWorkoutByWeeks(date)

            val resultDaylyWorkout = DailyStatisticsModel(
                id = resultWorkout.id,
                day = resultWorkout.day,
                workout = resultWorkout.workout.map { it.toDailyNew() }.toMutableList(),
                timeWorkout = 0
            )

            dailyStatisticsInteractor.insertDayWorkout(resultDaylyWorkout)
            readDayWorkout()
        }
    }
}