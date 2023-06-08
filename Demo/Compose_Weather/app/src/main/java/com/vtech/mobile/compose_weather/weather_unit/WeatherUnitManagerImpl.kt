package com.vtech.mobile.compose_weather.weather_unit

import android.content.SharedPreferences

class WeatherUnitManagerImpl(
    private val sharedPreferences: SharedPreferences
) : WeatherUnitManager {

    private val listeners = ArrayList<WeatherUnitManager.Listener>()
    private var weatherUnit = WeatherUnit.METRIC
    private var loaded = false

    override fun getWeatherUnit(): WeatherUnit {
        loadIfNeeded()
        return weatherUnit
    }

    override fun setWeatherUnit(weatherUnit: WeatherUnit) {
        loadIfNeeded()
        if (this.weatherUnit == weatherUnit) {
            return
        }
        this.weatherUnit = weatherUnit
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: WeatherUnitManager.Listener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: WeatherUnitManager.Listener) {
        listeners.remove(listener)
    }

    private fun loadIfNeeded() {
        if (loaded) {
            return
        }
        weatherUnit = when (sharedPreferences.getString("unit", "metric")) {
            "standard" -> WeatherUnit.STANDARD
            "metric" -> WeatherUnit.METRIC
            "imperial" -> WeatherUnit.IMPERIAL
            else -> WeatherUnit.METRIC
        }
        loaded = true
    }

    private fun save() {
        sharedPreferences.edit()
            .putString(
                "unit",
                when (weatherUnit) {
                    WeatherUnit.STANDARD -> "standard"
                    WeatherUnit.METRIC -> "metric"
                    WeatherUnit.IMPERIAL -> "imperial"
                }
            ).apply()
    }

    companion object {
        const val PREFERENCE_NAME = "weather_unit"
    }
}