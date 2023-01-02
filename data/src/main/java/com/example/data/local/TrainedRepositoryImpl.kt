package com.example.data.local

import com.example.data.local.dao.WorkoutDao
import com.example.domain.repository.TrainedRepository
import com.example.domain.model.DayWorkoutModel
import com.example.domain.model.SportsmanModel
import com.example.domain.model.WorkoutModel
import com.example.data.toDomain
import com.example.data.toEntity

class TrainedRepositoryImpl constructor(
    private val workoutDao: WorkoutDao,
): TrainedRepository {

    override suspend fun insertUser(user: SportsmanModel) {
        workoutDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: SportsmanModel) {
        workoutDao.updateUser(user.toEntity())
    }

    override suspend fun deleteUser(user: SportsmanModel) {

        workoutDao.deleteUser(user.toEntity())
    }

    override suspend fun readUserTable(): SportsmanModel? {

        return workoutDao.readUserTable()?.toDomain()
    }

    override suspend fun getSizeSportsmanTable(): Int {
        return workoutDao.getSizeSportsmanTable()
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
        return workoutDao.insertDayWorkout(workout.toEntity())
    }

    override suspend fun updateDayWorkout(workout: DayWorkoutModel) {
        workoutDao.updateDayWorkout(workout.toEntity())
    }

    override suspend fun readDayWorkout(): List<DayWorkoutModel>? {
        return workoutDao.readDayWorkout()?.map { it.toDomain() }
    }

    override suspend fun getDayWorkoutById(id: Int): DayWorkoutModel {
        return workoutDao.getDayWorkoutById(id).toDomain()
    }

    override suspend fun getSizeDayWorkoutTable(): Int {
        return workoutDao.getSizeDayWorkoutTable()
    }

    override suspend fun getDayWorkoutByWorkoutId(id: Int): DayWorkoutModel {
        return  workoutDao.getDayWorkoutByWorkoutId(id).toDomain()
    }
}