package com.example.trained.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
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
        if(ms == 0L) {
            return "0м"
        }

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


    fun getDate(): LocalDate {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val data = dateFormat.format(Date())


        val firstApiFormat =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        } else {
            TODO()
        }
        return LocalDate.parse(data, firstApiFormat)
    }

    fun getDecryptedWeek(week: String): String {
//        MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY
        return when(week) {
            "MONDAY" -> "Понедельник"
            "TUESDAY" -> "Вторник"
            "WEDNESDAY" -> "Среда"
            "THURSDAY" -> "Четверг"
            "FRIDAY" -> "Пятница"
            "SATURDAY" -> "Суббота"
            "SUNDAY" -> "Воскресенье"
            else -> "НИЧЕГО"
        }
    }

    fun getDecryptedWeekAccusativeForm(week: String): String {
        return when(week) {
            "MONDAY" -> "Понедельника"
            "TUESDAY" -> "Вторника"
            "WEDNESDAY" -> "Среды"
            "THURSDAY" -> "Четверга"
            "FRIDAY" -> "Пятницы"
            "SATURDAY" -> "Субботы"
            "SUNDAY" -> "Воскресенья"
            else -> "НИЧЕГО"
        }
    }
}