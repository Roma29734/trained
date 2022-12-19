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
        return repository.getSizeSportsmanTable() != 0
    }
}