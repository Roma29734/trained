package com.example.trained.data.local

import androidx.lifecycle.LiveData
import com.example.trained.data.local.dao.DayWorkoutDao
import com.example.trained.data.local.dao.ProfileDao
import com.example.trained.data.local.dao.WorkoutDao
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.SportsmanModel
import com.example.trained.data.model.WorkoutModel
import com.example.trained.domain.repository.TrainedRepository
import javax.inject.Inject

class TrainedRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val workoutDao: WorkoutDao,
    private val dayWorkoutDao: DayWorkoutDao,
): TrainedRepository {
    override suspend fun insertUser(user: SportsmanModel) {
        profileDao.insertUser(user)
    }

    override suspend fun updateUser(user: SportsmanModel) {
        profileDao.updateUser(user)
    }

    override suspend fun deleteUser(user: SportsmanModel) {
        profileDao.deleteUser(user)
    }

    override suspend fun readUserTable(): SportsmanModel {
        return profileDao.readUserTable()
    }

    override suspend fun getSizeSportsmanTable(): Int {
        return profileDao.getSizeSportsmanTable()
    }

    override suspend fun insertWorkout(day: WorkoutModel) {
        workoutDao.insertWorkout(day)
    }

    override suspend fun updateWorkout(day: WorkoutModel) {
        workoutDao.updateWorkout(day)
    }

    override suspend fun readWorkoutTable(): List<WorkoutModel> {
        return workoutDao.readWorkoutTable()
    }

    override suspend fun getSizeWorkoutTable(): Int {
        return workoutDao.getSizeWorkoutTable()
    }

    override suspend fun insertDayWorkout(workout: DayWorkoutModel) {
        dayWorkoutDao.insertDayWorkout(workout)
    }

    override suspend fun updateDayWorkout(workout: DayWorkoutModel) {
        dayWorkoutDao.updateDayWorkout(workout)
    }

    override suspend fun readDayWorkout(): DayWorkoutModel {
        return  dayWorkoutDao.readDayWorkout()
    }

}