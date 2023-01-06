package com.example.trained.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object Utils {

    fun formattedTimeMain(ms: Long): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)

        val second = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return (if (minutes >= 1) "${minutes}:" else "00:") +
                (if (second >= 1) "$second" else "00")
    }

    fun formattedWatchWidget(ms: Long): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return (if (hours >= 1) "${hours}ч " else "") +
                (if (minutes >= 1) "${minutes}мин " else "") +
                if (seconds >= 1) "${seconds}с" else ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(): LocalDate {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDate.parse("2019-08-07 09:00:00", firstApiFormat)
    }

    fun getDecryptedWeek(week: String): String {
//        MONDAY TUESDAY WEDNESDAY THURSDAY SATURDAY SUNDAY
        return when(week) {
            "MONDAY" -> "Понедельник"
            "TUESDAY" -> "Вторник"
            "WEDNESDAY" -> "Среда"
            "THURSDAY" -> "Четверг"
            "SATURDAY" -> "Суббота"
            "SUNDAY" -> "Воскресенье"
            else -> "НИЧЕГО"
        }
    }
}