package com.example.trained.di.module

import com.example.domain.repository.TrainedRepository
import com.example.domain.userCase.DailyStatisticsInteractor
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainBaseModule {

    @Provides
    fun provideProfileInteracror(repository: TrainedRepository): ProfileInteractor =
        ProfileInteractor(repository = repository)

    @Provides
    fun provideWorkoutInteractor(repository: TrainedRepository): WorkoutInteractor =
        WorkoutInteractor(repository = repository)

    @Provides
    fun provideDayWorkoutInteractor(repository: TrainedRepository): DailyStatisticsInteractor =
        DailyStatisticsInteractor(repository = repository)

}