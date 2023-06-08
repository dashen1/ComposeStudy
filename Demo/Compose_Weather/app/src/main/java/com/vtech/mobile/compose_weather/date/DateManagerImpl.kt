package com.vtech.mobile.compose_weather.date

import java.text.SimpleDateFormat
import java.util.*

class DateManagerImpl:DateManager {
    override fun convertTimestampToSpecificFormat1(timestampSecond: Long): String {
        val locale = Locale.getDefault()
        val simpleDataFormat = SimpleDateFormat("E dd",locale)
        return simpleDataFormat.format(Date(timestampSecond * 1_000)).capitalize(locale)
    }
}