package com.example.trained.di.module

import com.example.trained.ui.screen.firstStartApp.dayAddWorkout.DayAddWorkoutFragment
import com.example.trained.ui.screen.firstStartApp.start.StartFragment
import com.example.trained.ui.screen.firstStartApp.startConfig.StartConfigFragment
import com.example.trained.ui.screen.mainApp.addWorkout.AddWorkoutFragment
import com.example.trained.ui.screen.mainApp.appSettings.AppSettingsFragment
import com.example.trained.ui.screen.mainApp.config.ConfigFragment
import com.example.trained.ui.screen.mainApp.dayConfig.DayConfigFragment
import com.example.trained.ui.screen.mainApp.home.HomeFragment
import com.example.trained.ui.screen.mainApp.nav.NavFragment
import com.example.trained.ui.screen.mainApp.profile.ProfileFragment
import com.example.trained.ui.screen.mainApp.profileSettings.ProfileSettingsFragment
import com.example.trained.ui.screen.mainApp.updateConfig.UpdateConfigFragment
import com.example.trained.ui.screen.mainApp.updateProfile.UpdateProfileFragment
import com.example.trained.ui.screen.workout.choseWorkout.ChoseWorkoutFragment
import com.example.trained.ui.screen.workout.finishedWorkout.FinishedWorkoutFragment
import com.example.trained.ui.screen.workout.settingExercise.SettingExerciseFragment
import com.example.trained.ui.screen.workout.stopwatch.StopwatchFragment
import com.example.trained.ui.screen.workout.timerChill.TimerChillFragment
import com.example.trained.ui.screen.workout.transition.TransitionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

//    Start
    @ContributesAndroidInjector
    abstract fun contributeDayAddWorkoutFragment(): DayAddWorkoutFragment

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeStartConfigFragment(): StartConfigFragment

//    Main
    @ContributesAndroidInjector
    abstract fun contributeAddWorkoutFragment(): AddWorkoutFragment

    @ContributesAndroidInjector
    abstract fun contributeConfigFragment(): ConfigFragment

    @ContributesAndroidInjector
    abstract fun contributeDayConfigFragment(): DayConfigFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNavFragment(): NavFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileSettingsFragment(): ProfileSettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeUpdateConfigFragment(): UpdateConfigFragment

    @ContributesAndroidInjector
    abstract fun contributeUpdateProfileFragment(): UpdateProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeAppSettingsFragment(): AppSettingsFragment
//    Workout
    @ContributesAndroidInjector
    abstract fun contributeChoseWorkoutFragment(): ChoseWorkoutFragment

    @ContributesAndroidInjector
    abstract fun contributeFinishedWorkoutFragment(): FinishedWorkoutFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingExerciseFragment(): SettingExerciseFragment

    @ContributesAndroidInjector
    abstract fun contributeStopwatchFragment(): StopwatchFragment

    @ContributesAndroidInjector
    abstract fun contributeTimerChillFragment(): TimerChillFragment

    @ContributesAndroidInjector
    abstract fun contributeTransitionFragment(): TransitionFragment
}