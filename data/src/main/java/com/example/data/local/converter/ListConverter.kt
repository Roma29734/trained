package com.example.data.local.converter

import androidx.room.TypeConverter
import com.example.data.model.DailyWorkoutModel
import com.example.data.model.WorkoutDayModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): MutableList<WorkoutDayModel>? {
        if(data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: MutableList<WorkoutDayModel>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun fromDailyStatistics(data: String?): MutableList<DailyWorkoutModel>? {
        if(data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToStringDailyStatistics(someObjects: MutableList<DailyWorkoutModel>?): String? {
        return gson.toJson(someObjects)
    }
}