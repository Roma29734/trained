package com.example.trained.ui.screen.profileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.SportsmanEntity
import com.example.domain.TrainedRepository
import com.example.domain.model.SportsmanModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    private val repository: TrainedRepository,
): ViewModel() {

    fun inputuser(name: String, age: String, weight: String, growth: String) {
        viewModelScope.launch {
            repository.insertUser(
                SportsmanModel  (
                    0,
                    name,
                    age.toInt(),
                    weight.toInt(),
                    growth.toInt()
                )
            )
        }
    }
}