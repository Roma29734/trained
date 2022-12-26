package com.example.data.local

import com.example.data.local.dao.DayWorkoutDao
import com.example.data.local.dao.ProfileDao
import com.example.data.local.dao.WorkoutDao
import com.example.domain.TrainedRepository
import com.example.domain.model.DayWorkoutModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel
import com.example.data.toDomain
import com.example.data.toEntity

class TrainedRepositoryImpl constructor(
    private val profileDao: ProfileDao,
    private val workoutDao: WorkoutDao,
    private val dayWorkoutDao: DayWorkoutDao,
): TrainedRepository {

    override suspend fun insertUser(user: SportsmanModel) {
        profileDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: SportsmanModel) {
        profileDao.updateUser(user.toEntity())
    }

    override suspend fun deleteUser(user: SportsmanModel) {

        profileDao.deleteUser(user.toEntity())
    }

    override suspend fun readUserTable(): SportsmanModel? {

        return profileDao.readUserTable()?.toDomain()
    }

    override suspend fun getSizeSportsmanTable(): Int {
        return profileDao.getSizeSportsmanTable()
    }

    override suspend fun insertWorkout(day: WorkoutModel): Long {

        return workoutDao.insertWorkout(day.toEntity())
    }

    override suspend fun updateWorkout(day: WorkoutModel) {

        workoutDao.updateWorkout(day.toEntity())
    }

    override suspend fun readWorkoutTable(): List<WorkoutModel> {

        return workoutDao.readWorkoutTable().map { it.toDomain() }
    }

    override suspend fun getSizeWorkoutTable(): Int {
        return workoutDao.getSizeWorkoutTable()
    }

    override suspend fun insertDayWorkout(workout: DayWorkoutModel): Long {
        return dayWorkoutDao.insertDayWorkout(workout.toEntity())
    }

    override suspend fun updateDayWorkout(workout: DayWorkoutModel) {
        dayWorkoutDao.updateDayWorkout(workout.toEntity())
    }

    override suspend fun readDayWorkout(): List<DayWorkoutModel>? {
        return dayWorkoutDao.readDayWorkout()?.map { it.toDomain() }
    }

    override suspend fun getDayWorkoutById(id: Int): DayWorkoutModel {
        return dayWorkoutDao.getDayWorkoutById(id).toDomain()
    }

    override suspend fun getSizeDayWorkoutTable(): Int {
        return  dayWorkoutDao.getSizeDayWorkoutTable()
    }
}