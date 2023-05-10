package com.example.data.model

import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.SportsmanModel

data class HomeModel(
    val dailyWorkoutModel: DailyStatisticsModel,
    val sportsmanModel: SportsmanModel,
    val completeApproach: Int,
    val sumApproach: Int,
)