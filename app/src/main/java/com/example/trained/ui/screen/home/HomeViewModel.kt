package com.example.trained.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.TrainedRepository
import com.example.domain.model.DayWorkoutModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TrainedRepository
): ViewModel() {

    private var _profile: MutableLiveData<SportsmanModel>? = MutableLiveData()
    val profileData get() = _profile

    private var _workout: MutableLiveData<List<WorkoutModel>>? = MutableLiveData()
    val workout get() = _workout

    private var _dayWorkout: MutableLiveData<List<DayWorkoutModel>> = MutableLiveData()
    val dayWorkout get() = _dayWorkout

    fun readProfile() {
        viewModelScope.launch {
            _profile?.value = repository.readUserTable()
        }
    }


    fun readDayWorkout() {
        viewModelScope.launch(Dispatchers.IO) {

            if(repository.getSizeWorkoutTable() == 0) {
                return@launch
            }

            if(checkDayWorkout()) {
                viewModelScope.launch(Dispatchers.Main) {
                    _dayWorkout.value = repository.readDayWorkout()
                }
            } else {
                fillDayWorkout()
            }
        }
    }

    private fun fillDayWorkout() {
        viewModelScope.launch (Dispatchers.IO) {
            val repeatSize = repository.getSizeWorkoutTable()
            val resultWorkout = repository.readWorkoutTable()
            for(i in 0..repeatSize) {
                val model = DayWorkoutModel(
                    id = 0,
                    idWorkout = resultWorkout[i].id,
                    nameWorkout = resultWorkout[i].nameExercise,
                    sumApproach = resultWorkout[i].approaches,
                    completedApproach = 0,
                    receptions = resultWorkout[i].repetitions,
                    timeWorkout = 0,
                )
                repository.insertDayWorkout(model)
            }
            readDayWorkout()
        }
    }

    private suspend fun checkDayWorkout(): Boolean {
        return repository.getSizeDayWorkoutTable() != 0
    }
}