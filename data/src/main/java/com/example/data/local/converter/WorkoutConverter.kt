package com.example.data.local.converter

import androidx.room.TypeConverter
import com.example.data.model.WorkoutDayModel
import com.google.gson.Gson

class WorkoutConverter {

    @TypeConverter
    fun listToJson(value: List<WorkoutDayModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<WorkoutDayModel>::class.java).toList()
}