package com.vtech.mobile.compose_weather.weaher

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.weather_api.WeatherApiModule
import com.vtech.mobile.compose_weather.weather_repository.WeatherRepository
import com.vtech.mobile.compose_weather.weather_repository.WeatherRepositoryModule

class WeatherModule {

    fun createWeatherManager():WeatherManager{
        return WeatherManagerImpl(
            WeatherApiModule().createWeatherApiManager(),
            WeatherGraph.getCityManager(),
            WeatherGraph.getDateManager(),
            WeatherRepositoryModule().createWeatherRepository(),
            WeatherGraph.getWeatherUnitManager(),
            createWeatherManagerImplAddOn()
        )
    }

    private fun createWeatherManagerImplAddOn() = object :WeatherManagerImpl.AddOn{

        private val workerThread by lazy { createWorkerThread() }
        private val workerThreadHandler by lazy { Handler(workerThread.looper) }
        private val mainHandler by lazy { Handler(Looper.getMainLooper()) }

        override fun postWorkerThread(runnable: Runnable) {
            workerThreadHandler.post(runnable)
        }

        override fun postMainThread(runnable: Runnable) {
            mainHandler.post(runnable)
        }

        private fun createWorkerThread():HandlerThread{
            val tmp = HandlerThread("weather_load")
            tmp.start()
            return tmp
        }
    }
}