package com.example.trained.di

import com.example.domain.repository.TrainedRepository
import com.example.domain.userCase.DayWorkoutInteractor
import com.example.domain.userCase.ProfileInteractor
import com.example.domain.userCase.WorkoutInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class DomainBaseModule {

    @Provides
    fun provideProfileInteracror(repository: TrainedRepository): ProfileInteractor =
        ProfileInteractor(repository = repository)

    @Provides
    fun provideWorkoutInteractor(repository: TrainedRepository): WorkoutInteractor =
        WorkoutInteractor(repository = repository)

    @Provides
    fun provideDayWorkoutInteractor(repository: TrainedRepository): DayWorkoutInteractor =
        DayWorkoutInteractor(repository = repository)

}