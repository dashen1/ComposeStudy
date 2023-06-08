package com.vtech.mobile.compose_weather.graph

class WeatherGraphInitializationModule {

    fun createWeatherGraphInitializationManager(): WeatherGraphInitializationManager {
        return WeatherGraphInitializationManagerImpl(
            WeatherGraph.getCityManager(),
            WeatherGraph.getWeatherManager(),
            WeatherGraph.getWeatherUnitManager()
        )
    }
}