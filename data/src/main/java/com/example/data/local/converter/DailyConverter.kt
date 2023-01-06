package com.example.data.local.converter

import androidx.room.TypeConverter
import com.example.data.model.DailyWorkoutModel
import com.google.gson.Gson

class DailyConverter {

    @TypeConverter
    fun listToJson(value: List<DailyWorkoutModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<DailyWorkoutModel>::class.java).toList()
}