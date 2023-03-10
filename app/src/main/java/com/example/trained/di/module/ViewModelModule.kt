package com.example.trained.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trained.ui.MainViewModel
import com.example.trained.ui.screen.mainApp.addWorkout.AddWorkoutViewModel
import com.example.trained.ui.screen.mainApp.config.ConfigViewModel
import com.example.trained.ui.screen.mainApp.dayConfig.DayConfigViewModel
import com.example.trained.ui.screen.mainApp.home.HomeViewModel
import com.example.trained.ui.screen.mainApp.nav.NavViewModel
import com.example.trained.ui.screen.mainApp.updateConfig.UpdateConfigViewModel
import com.example.trained.ui.screen.workout.choseWorkout.ChoseWorkoutViewModel
import com.example.trained.ui.screen.workout.finishedWorkout.FinishedViewModel
import com.example.trained.ui.viewModel.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(imagesListViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddWorkoutViewModel::class)
    abstract fun bindAddWorkoutViewModel(AddWorkoutViewModel: AddWorkoutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfigViewModel::class)
    abstract fun bindImagesListViewModel(imagesListViewModel: ConfigViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DayConfigViewModel::class)
    abstract fun bindDayConfigViewModel(imagesListViewModel: DayConfigViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(imagesListViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavViewModel::class)
    abstract fun bindNavViewModel(imagesListViewModel: NavViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(UpdateConfigViewModel::class)
    abstract fun bindUpdateConfigViewModel(imagesListViewModel: UpdateConfigViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChoseWorkoutViewModel::class)
    abstract fun bindChoseWorkoutViewModel(imagesListViewModel: ChoseWorkoutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FinishedViewModel::class)
    abstract fun bindFinishedViewModel(imagesListViewModel: FinishedViewModel): ViewModel
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)