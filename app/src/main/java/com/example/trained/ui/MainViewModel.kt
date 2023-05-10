package com.example.trained.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutDayDomainModel
import com.example.domain.model.WorkoutModel
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import com.example.trained.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val workoutInteractor: WorkoutInteractor,
) : ViewModel() {

    private var _profile: MutableLiveData<SportsmanModel> = MutableLiveData()
    val profileData get() = _profile

    fun readProfile() {
        viewModelScope.launch {
            _profile.value = profileInteractor.readUserTable()
        }
    }

    fun inputUser(name: String, age: String, weight: String, growth: String) {
        viewModelScope.launch {
            profileInteractor.insertUser(
                SportsmanModel(
                    0,
                    name,
                    age.toInt(),
                    weight.toInt(),
                    growth.toInt()
                )
            )
        }
    }

    fun updateProfile(age: String, weight: String, growth: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val stockModel = profileInteractor.readUserTable()
            val name = stockModel!!.name
            val id = stockModel.id
            val user = SportsmanModel(
                id = id,
                name = name,
                age = age.toInt(),
                weight = weight.toInt(),
                growth = growth.toInt()
            )
            profileInteractor.updateUser(user)
        }
    }

    fun startSettingWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            if (workoutInteractor.getSizeWorkoutTable() != 0) return@launch
            val nameWeeks =
                listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")

            Log.d("startViewModel",nameWeeks.size.toString())

            for (i in nameWeeks.indices) {
                Log.d("startViewModel","$i")
                val model = WorkoutModel(
                    id = 0,
                    day = nameWeeks[i],
                    workout = mutableListOf()
                )
                workoutInteractor.insertWorkout(model)
            }
            Log.d("startViewModel", "Созданны базовые 7 дней")
        }
    }


    fun saveWorkout(
        name: String,
        repetitions: String,
        approaches: String,
        workoutModel: WorkoutModel,
        projectileWeight: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val modelWorkout = WorkoutDayDomainModel(name, repetitions.toInt(), approaches.toInt(), projectileWeight.toInt())

            val newWorkout = workoutModel.workout
            newWorkout.add(modelWorkout)

            val updateWorkoutModel = WorkoutModel(
                id = workoutModel.id,
                day = workoutModel.day,
                workout = newWorkout,
            )

            workoutInteractor.updateWorkout(updateWorkoutModel)
        }

    }

    private var _dayWorkout: MutableLiveData<WorkoutModel> = MutableLiveData()
    val dayWorkout get() = _dayWorkout


    fun getDayWorkout() {
        viewModelScope.launch (Dispatchers.IO) {
            val date = Utils.getDate().dayOfWeek.toString()
            _dayWorkout.postValue(workoutInteractor.getWorkoutByWeeks(date))
        }
    }
}