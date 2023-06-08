package com.vtech.mobile.compose_weather.main_view

import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weaher.WeatherManager

class MainViewPresenter(
    private val screen: MainViewContract.Screen,
    private val weatherManager: WeatherManager
) : MainViewContract.UserAction {

    private var weathers = MutableLiveData(createWeathers())
    private var error = MutableLiveData(createError())

    init {
        weatherManager.addListener(createWeatherListener())
    }

    override fun getWeathers(): MutableLiveData<List<Weather>> {
        return weathers
    }

    override fun getError(): MutableLiveData<Boolean> {
        return error
    }

    private fun updateWeathers() {
        weathers.value = createWeathers()
    }

    private fun updateError() {
        error.value = createError()
    }

    private fun createWeathers(): List<Weather> {
        return weatherManager.getWeathers()
    }

    private fun createError(): Boolean {
        return weatherManager.getWeathers().size < 4
    }

    private fun createWeatherListener() = object : WeatherManager.Listener {
        override fun onChanged() {
            updateWeathers()
            updateError()
        }

        override fun onFailed() {
            updateError()
        }

    }
}