package com.example.trained.ui.screen.mainApp.home

import com.example.data.model.HomeModel
import com.example.trained.utils.LoadState

data class HomeState(
    var loadState: LoadState = LoadState.SUCCESS,
    val successState: HomeModel? = null
)