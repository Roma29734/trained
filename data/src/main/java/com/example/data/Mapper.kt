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

fun DailyStatisticsEntity.toDomain() = workout?.map { it.toDomain() }?.let { result ->
    var projectileWeight = 0
    result.map { projectileWeight+=it.projectileWeight }
    DailyStatisticsModel (
        id = id,
        day = day,
        workout = result.toMutableList(),
        timeWorkout = timeWorkout,
        projectileWeight = projectileWeight
    )
}

fun DailyStatisticsModel.toTransit() = TransitDailyStatisticsModel (
    id = id,
    day = day,
    workout = workout.map { it.toData() }.toMutableList(),
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
    approaches = approaches,
    projectileWeight = projectileWeight
)

fun WorkoutDayDomainModel.toData() = WorkoutDayModel(
    nameExercise = nameExercise,
    repetitions = repetitions,
    approaches = approaches,
    projectileWeight = projectileWeight
)

fun DailyWorkoutModel.toDomain() = DailyWorkoutDomainModel(
    nameWorkout = nameWorkout,
    sumApproach = sumApproach,
    completedApproach = completedApproach,
    receptions = receptions,
    projectileWeight = projectileWeight
)

fun DailyWorkoutDomainModel.toData() = DailyWorkoutModel(
    nameWorkout = nameWorkout,
    sumApproach = sumApproach,
    completedApproach = completedApproach,
    receptions = receptions,
    projectileWeight = projectileWeight
)

fun WorkoutDayDomainModel.toDailyNew() = DailyWorkoutDomainModel(
    nameWorkout = nameExercise,
    sumApproach = approaches,
    completedApproach = 0,
    receptions = repetitions,
    projectileWeight = projectileWeight
)

fun WorkoutModel.toConfigAdapterModel() = ConfigAdapterModel(
    id = id,
    day = day,
    workout = workout,
    openState = false,
)