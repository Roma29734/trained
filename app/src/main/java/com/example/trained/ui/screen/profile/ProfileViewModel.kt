package com.example.trained.ui.screen.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trained.data.model.SportsmanModel
import com.example.trained.domain.repository.TrainedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: TrainedRepository
): ViewModel() {

    private var _profile: MutableLiveData<SportsmanModel> = MutableLiveData()
    val profileData get() = _profile

    fun readProfile() {
        viewModelScope.launch {
            _profile.value = repository.readUserTable()
        }
    }
}