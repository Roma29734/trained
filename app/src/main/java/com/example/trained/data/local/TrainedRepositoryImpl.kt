package com.example.trained.data.local

import com.example.trained.data.local.dao.ProfileDao
import com.example.trained.data.model.SportsmanModel
import com.example.trained.domain.repository.TrainedRepository
import javax.inject.Inject

class TrainedRepositoryImpl @Inject constructor(
    private val dao: ProfileDao
): TrainedRepository {
    override suspend fun insertUser(user: SportsmanModel) {
        dao.insertUser(user)
    }

    override suspend fun updateUser(user: SportsmanModel) {
        dao.updateUser(user)
    }

    override suspend fun deleteUser(user: SportsmanModel) {
        dao.deleteUser(user)
    }

    override suspend fun readUserTable(): SportsmanModel {
        return dao.readUserTable()
    }

}