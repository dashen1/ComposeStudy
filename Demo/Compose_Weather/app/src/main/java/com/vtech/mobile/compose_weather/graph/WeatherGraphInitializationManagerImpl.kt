package com.vtech.mobile.compose_weather.graph

import com.vtech.mobile.compose_weather.city.CityManager
import com.vtech.mobile.compose_weather.weaher.WeatherManager
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnitManager

class WeatherGraphInitializationManagerImpl(
    private val cityManager: CityManager,
    private val weatherManager: WeatherManager,
    private val weatherUnitManager: WeatherUnitManager
) : WeatherGraphInitializationManager {
    override fun initialize() {
        cityManager.addListener(createCityListener())
        weatherUnitManager.addListener(createWeatherUnitListener())
    }

    private fun createCityListener() = object : CityManager.Listener {
        override fun onChanged() {
            weatherManager.clearCache()
            weatherManager.load()
        }
    }

    private fun createWeatherUnitListener() = object : WeatherUnitManager.Listener {
        override fun onChanged() {
            weatherManager.clearCache()
            weatherManager.load()
        }

    }
}