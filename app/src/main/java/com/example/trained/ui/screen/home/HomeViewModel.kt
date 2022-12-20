package com.example.trained.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trained.data.model.SportsmanModel
import com.example.trained.data.model.WorkoutModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TrainedRepository
): ViewModel() {

    private var _profile: MutableLiveData<SportsmanModel> = MutableLiveData()
    val profileData get() = _profile

    private var _workout: MutableLiveData<List<WorkoutModel>> = MutableLiveData()
    val workout get() = _workout

    fun readProfile() {
        viewModelScope.launch {
            _profile.value = repository.readUserTable()
        }
    }

    fun readWorkout() {
        viewModelScope.launch {
            _workout.value = repository.readWorkoutTable()
        }
    }
}