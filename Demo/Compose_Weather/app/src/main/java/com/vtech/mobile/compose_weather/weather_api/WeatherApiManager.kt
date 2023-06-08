package com.vtech.mobile.compose_weather.weather_api

import androidx.annotation.IntRange
import androidx.annotation.WorkerThread
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnit

interface WeatherApiManager {

    @WorkerThread
    fun getWeather(
        city: String,
        weatherUnit: WeatherUnit
    ): Weather

    @WorkerThread
    fun getWeatherForecastDaily(
        city: String,
        weatherUnit: WeatherUnit,
        @IntRange(from = 1, to = 16)
        numberOfDays: Int
    ): List<Weather>
}