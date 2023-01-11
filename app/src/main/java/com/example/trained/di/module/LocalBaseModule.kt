package com.example.trained.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.local.TrainedRepositoryImpl
import com.example.data.local.dao.WorkoutDao
import com.example.data.local.dataBase.WorkoutDataBase
import com.example.domain.repository.TrainedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalBaseModule {

    @Provides
    fun provideTraindeRepository(impl: TrainedRepositoryImpl): TrainedRepository = impl

    @Provides
    fun provideTrainedRepositoryImpl(
        WorkoutDao: WorkoutDao,
    ) = TrainedRepositoryImpl(
        workoutDao = WorkoutDao,
    )

    @Provides
    fun provideWorkoutDao(appDataBase: WorkoutDataBase): WorkoutDao = appDataBase.workoutDao()

    @Provides
    @Singleton
    fun provideWorkoutDataBase(context: Application): WorkoutDataBase =
        Room.databaseBuilder(
            context,
            WorkoutDataBase::class.java,
            "workout_base"
        ).build()
}