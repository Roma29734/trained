package com.example.trained.ui.screen.mainApp.profileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SportsmanModel
import com.example.domain.userCase.ProfileInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


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