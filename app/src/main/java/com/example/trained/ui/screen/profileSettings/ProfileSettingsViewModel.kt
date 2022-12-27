package com.example.trained.ui.screen.profileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.TrainedRepository
import com.example.domain.model.SportsmanModel
import com.example.domain.userCase.ProfileInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
): ViewModel() {

    fun inputuser(name: String, age: String, weight: String, growth: String) {
        viewModelScope.launch {
            profileInteractor.insertUser(
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