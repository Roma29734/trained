package com.example.trained.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.TrainedRepositoryImpl
import com.example.data.local.dao.WorkoutDao
import com.example.data.local.dataBase.WorkoutDataBase
import com.example.domain.repository.TrainedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
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
    fun provideWorkoutDataBase(@ApplicationContext context: Context): WorkoutDataBase =
        Room.databaseBuilder(
            context,
            WorkoutDataBase::class.java,
            "workout_base"
        ).build()
}