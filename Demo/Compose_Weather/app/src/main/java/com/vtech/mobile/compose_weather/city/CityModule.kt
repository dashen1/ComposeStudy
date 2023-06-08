package com.vtech.mobile.compose_weather.city

import android.content.Context
import android.content.SharedPreferences
import com.vtech.mobile.compose_weather.graph.WeatherGraph

class CityModule {

    fun createCityManager(): CityManager {
        return CityManagerImpl(
            createSharedPreferences()
        )
    }

    private fun createSharedPreferences(): SharedPreferences {
        val context = WeatherGraph.getContext()
        return context.getSharedPreferences(
            CityManagerImpl.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }
}
