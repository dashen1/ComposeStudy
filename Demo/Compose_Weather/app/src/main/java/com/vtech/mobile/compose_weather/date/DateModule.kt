package com.vtech.mobile.compose_weather.date

class DateModule {

    fun createDateManager():DateManager{
        return DateManagerImpl()
    }
}