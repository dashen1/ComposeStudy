package com.vtech.mobile.compose_weather.graph

import android.content.Context
import com.vtech.mobile.compose_weather.city.CityModule
import com.vtech.mobile.compose_weather.date.DateModule
import com.vtech.mobile.compose_weather.network.NetworkModule
import com.vtech.mobile.compose_weather.ui.theme.ThemeModule
import com.vtech.mobile.compose_weather.weaher.WeatherModule
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnitModule

class WeatherGraph private constructor(
    private val context: Context
) {
    private val cityManager by lazy { CityModule().createCityManager() }
    private val dateManger by lazy { DateModule().createDateManager() }
    private val networkManager by lazy { NetworkModule().createNetWorkManager() }
    private val themeManager by lazy { ThemeModule().createThemeManager() }
    private val weatherGraphInitializationManager by lazy { WeatherGraphInitializationModule().createWeatherGraphInitializationManager() }
    private val weatherManager by lazy { WeatherModule().createWeatherManager() }
    private val weatherUnitManager by lazy { WeatherUnitModule().createWeatherUnitManager() }


    companion object {
        private var graph: WeatherGraph? = null
        fun initialize(context: Context) {
            if (graph != null) {
                return
            }
            graph = WeatherGraph(context.applicationContext)
            graph!!.weatherGraphInitializationManager.initialize()
        }

        fun getCityManager() = graph!!.cityManager
        fun getDateManager() = graph!!.dateManger
        fun getContext() = graph!!.context
        fun getNetworkManager() = graph!!.networkManager
        fun getThemeManager()= graph!!.themeManager
        fun getWeatherManager() = graph!!.weatherManager
        fun getWeatherUnitManager() = graph!!.weatherUnitManager
    }
}