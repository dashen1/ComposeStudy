package com.vtech.mobile.compose_weather.weather_repository

import com.vtech.mobile.compose_weather.weaher.Weather

interface WeatherRepository {

    fun getWeather():Weather?

    fun setWeather(weather: Weather?)

    fun getWeatherForecastDaily():List<Weather>

    fun setWeatherForecastDaily(weathers:List<Weather>)

    fun addListener(listener:Listener)

    fun removerListener(listener:Listener)

    interface Listener{
        fun onChanged()
    }
}