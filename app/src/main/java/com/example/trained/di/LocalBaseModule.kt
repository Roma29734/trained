package com.example.trained.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.TrainedRepositoryImpl
import com.example.data.local.dao.DayWorkoutDao
import com.example.data.local.dao.ProfileDao
import com.example.data.local.dao.WorkoutDao
import com.example.data.local.dataBase.DayWorkoutDataBase
import com.example.data.local.dataBase.SportsmanDataBase
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
        ProfileDao: ProfileDao,
        WorkoutDao: WorkoutDao,
        DayWorkoutDao: DayWorkoutDao
    ) = TrainedRepositoryImpl(
        profileDao = ProfileDao,
        workoutDao = WorkoutDao,
        dayWorkoutDao = DayWorkoutDao
    )

    @Provides
    fun provideProfileDao(appDataBase: SportsmanDataBase): ProfileDao = appDataBase.profileDao()

    @Provides
    fun provideWorkoutDao(appDataBase: WorkoutDataBase): WorkoutDao = appDataBase.workoutDao()

    @Provides
    fun provideDayWorkoutDao(appDatabase: DayWorkoutDataBase): DayWorkoutDao =
        appDatabase.dayWorkoutDao()

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

    @Provides
    @Singleton
    fun provideDayWorkoutDataBase(@ApplicationContext context: Context): DayWorkoutDataBase =
        Room.databaseBuilder(
            context,
            DayWorkoutDataBase::class.java,
            "day_workout_table"
        ).build()
}