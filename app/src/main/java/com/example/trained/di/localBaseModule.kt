package com.example.trained.di

import android.content.Context
import androidx.room.Room
import com.example.trained.data.local.TrainedRepositoryImpl
import com.example.trained.data.local.dao.ProfileDao
import com.example.trained.data.local.dao.WorkoutDao
import com.example.trained.data.local.dataBase.SportsmanDataBase
import com.example.trained.data.local.dataBase.WorkoutDataBase
import com.example.trained.domain.repository.TrainedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class localBaseModule {

    @Provides
    fun provideTraindeRepository(impl: TrainedRepositoryImpl): TrainedRepository = impl

    @Provides
    fun provideProfileDao(appDataBase: SportsmanDataBase): ProfileDao = appDataBase.profileDao()

    @Provides
    fun provideWorkoutDao(appDataBase: WorkoutDataBase): WorkoutDao = appDataBase.workoutDao()

    @Provides
    @Singleton
    fun provideSportsmanDataBase(@ApplicationContext context: Context): SportsmanDataBase =
        Room.databaseBuilder(
            context,
            SportsmanDataBase::class.java,
            "sportsman_table"
        ).build()

    @Provides
    @Singleton
    fun provideWorkoutDataBase(@ApplicationContext context: Context): WorkoutDataBase =
        Room.databaseBuilder(
            context,
            WorkoutDataBase::class.java,
            "workout_base"
        ).build()
}