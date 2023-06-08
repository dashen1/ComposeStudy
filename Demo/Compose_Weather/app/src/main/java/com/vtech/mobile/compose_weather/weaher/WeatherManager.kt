package com.vtech.mobile.compose_weather.weaher

interface WeatherManager {

    fun load()

    /**
     * 0 is today, 1 is tomorrow
     */
    fun getWeathers():List<Weather>

    fun clearCache()

    fun addListener(listener:Listener)

    fun removeListener(listener:Listener)

    interface Listener{

        fun onChanged()

        fun onFailed()
    }
}