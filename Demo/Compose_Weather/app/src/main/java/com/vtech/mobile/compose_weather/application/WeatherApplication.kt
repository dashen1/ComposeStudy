package com.vtech.mobile.compose_weather.application

import android.app.Application
import com.vtech.mobile.compose_weather.graph.WeatherGraph

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        WeatherGraph.initialize(this)
    }
}