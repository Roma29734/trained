package com.example.trained.domain.repository

import androidx.lifecycle.LiveData
import com.example.trained.data.model.SportsmanModel

interface TrainedRepository {

    suspend fun insertUser(user: SportsmanModel)

    suspend fun updateUser(user: SportsmanModel)

    suspend fun deleteUser(user: SportsmanModel)

    suspend fun readUserTable(): SportsmanModel

    suspend fun getSizeSportsmanTable(): Int
}