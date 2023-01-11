package com.example.trained.ui.screen.mainApp.nav

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import javax.inject.Inject


class NavViewModel @Inject  constructor(
    private val profileInteractor: ProfileInteractor,
    private val workoutInteractor: WorkoutInteractor,
) : ViewModel() {

    suspend fun checkUser(): Boolean {
        val result = profileInteractor.getSizeSportsmanTable() != 0
        Log.d("checkBagFirstStart", result.toString())
        return result
    }

    suspend fun checkWorkout(): Boolean {
        return workoutInteractor.getSizeWorkoutTable() != 0
    }
}