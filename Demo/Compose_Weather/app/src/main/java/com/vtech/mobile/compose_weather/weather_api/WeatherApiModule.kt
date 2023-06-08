package com.vtech.mobile.compose_weather.weather_api

import com.vtech.mobile.compose_weather.graph.WeatherGraph

class WeatherApiModule {

    fun createWeatherApiManager():WeatherApiManager{
        return WeatherApiManagerImpl(
            WeatherGraph.getNetworkManager()
        )
    }
}