package com.example.trained.ui.screen.mainApp.profile

import com.example.data.model.HomeModel
import com.example.domain.model.SportsmanModel
import com.example.trained.utils.LoadState

data class ProfileState(
    val profileData: SportsmanModel? = null,
    val trainedOfWeek: Int,
)