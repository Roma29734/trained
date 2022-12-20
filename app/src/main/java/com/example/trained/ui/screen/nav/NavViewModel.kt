package com.example.trained.ui.screen.nav

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavViewModel @Inject  constructor(
    private var repository: TrainedRepository
) : ViewModel() {

    suspend fun checkUser(): Boolean {
        val result = repository.getSizeSportsmanTable() != 0
        Log.d("checkBagFirstStart", result.toString())
        return result
    }

    suspend fun checkWorkout(): Boolean {
        return repository.getSizeWorkoutTable() != 0
    }
}