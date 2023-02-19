package com.example.trained.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SportsmanModel
import com.example.domain.userCase.ProfileInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
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
}