package com.example.kotlinblog.utils

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

class Utilities {

    fun getRelativeTime(timeString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val timeInMillis = sdf.parse(timeString)?.time ?: 0L

        return DateUtils.getRelativeTimeSpanString(
            timeInMillis,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormalTime(timeString: String): String {
        val zonedDateTime = ZonedDateTime.parse(timeString)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyy")
        return zonedDateTime.format(formatter)
    }
}
