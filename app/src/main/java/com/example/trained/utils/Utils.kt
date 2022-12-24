package com.example.trained.utils

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
}