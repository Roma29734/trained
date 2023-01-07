package com.example.data

import com.example.data.model.*
import com.example.data.model.entity.DailyStatisticsEntity
import com.example.data.model.entity.SportsmanEntity
import com.example.data.model.entity.WorkoutEntity
import com.example.data.model.transit.TransitDailyStatisticsModel
import com.example.data.model.transit.TransitWorkoutModel
import com.example.domain.model.*

fun WorkoutModel.toEntity() = WorkoutEntity (
    id = id,
    day = day,
    workout = workout.map { it.toData() }.toMutableList()
)

fun WorkoutEntity.toDomain() = WorkoutModel (
    id = id,
    day = day,
    workout = workout.map { it.toDomain() }.toMutableList()
)

fun SportsmanEntity.toDomain() = SportsmanModel (
    id = id,
    name = name,
    age = age,
    weight = weight,
    growth = growth,
)

fun SportsmanModel.toEntity() = SportsmanEntity (
    id = id,
    name = name,
    age = age,
    weight = weight,
    growth = growth,
)

fun DailyStatisticsModel.toEntity() = DailyStatisticsEntity (
    id = id,
    day = day,
    workout = workout.map { it.toData() }.toMutableList(),
    timeWorkout = timeWorkout
)

fun DailyStatisticsEntity.toDomain() = workout?.map { it.toDomain() }?.let {
    DailyStatisticsModel (
        id = id,
        day = day,
        workout = it.toMutableList(),
        timeWorkout = timeWorkout
    )
}

fun DailyStatisticsModel.toTransit() = TransitDailyStatisticsModel (
    id = id,
    day = day,
    workout = workout.map { it.toData() }.toMutableList(),
    timeWorkout = timeWorkout
)

fun TransitDailyStatisticsModel.toDomain() = DailyStatisticsModel (
    id = id,
    day = day,
    workout = workout.map { it.toDomain() }.toMutableList(),
    timeWorkout = timeWorkout
)

fun WorkoutModel.toTransit() = TransitWorkoutModel (
    id = id,
    day = day,
    workout = workout.map { it.toData() }.toMutableList()
)

fun TransitWorkoutModel.toDomain() = WorkoutModel (
    id = id,
    day = day,
    workout = workout.map { it.toDomain() }.toMutableList()
)

fun WorkoutDayModel.toDomain() = WorkoutDayDomainModel(
    nameExercise = nameExercise,
    repetitions = repetitions,
    approaches = approaches
)

fun WorkoutDayDomainModel.toData() = WorkoutDayModel(
    nameExercise = nameExercise,
    repetitions = repetitions,
    approaches = approaches
)

fun DailyWorkoutModel.toDomain() = DailyWorkoutDomainModel(
    nameWorkout = nameWorkout,
    sumApproach = sumApproach,
    completedApproach = completedApproach,
    receptions = receptions,
)

fun DailyWorkoutDomainModel.toData() = DailyWorkoutModel(
    nameWorkout = nameWorkout,
    sumApproach = sumApproach,
    completedApproach = completedApproach,
    receptions = receptions,
)

fun WorkoutDayDomainModel.toDailyNew() = DailyWorkoutDomainModel(
    nameWorkout = nameExercise,
    sumApproach = approaches,
    completedApproach = 0,
    receptions = repetitions,
)

fun WorkoutModel.toConfigAdapterModel() = ConfigAdapterModel(
    id = id,
    day = day,
    workout = workout,
    openState = false,
)