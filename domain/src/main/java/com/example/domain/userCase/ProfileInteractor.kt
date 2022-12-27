package com.example.domain.userCase

import com.example.domain.model.SportsmanModel
import com.example.domain.repository.TrainedRepository

class ProfileInteractor constructor(
    private val repository: TrainedRepository,
) {
    suspend fun insertUser(user: SportsmanModel) = repository.insertUser(user)

    suspend fun updateUser(user: SportsmanModel) = repository.updateUser(user)

    suspend fun deleteUser(user: SportsmanModel) = repository.deleteUser(user)

    suspend fun readUserTable(): SportsmanModel? = repository.readUserTable()

    suspend fun getSizeSportsmanTable(): Int = repository.getSizeSportsmanTable()
}