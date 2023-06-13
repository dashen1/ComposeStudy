package com.vtech.mobile.compose_weather.main_view_top_bar_view

import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.city.CityManager
import com.vtech.mobile.compose_weather.date.DateManager
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weaher.WeatherManager
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnit
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnitManager

class MainViewTopBarViewPresenter(
    private val screen: MainViewTopBarViewContract.Screen,
    private val cityManager: CityManager,
    private val dateManager: DateManager,
    private val weatherManager: WeatherManager,
    private val weatherUnitManager: WeatherUnitManager
) : MainViewTopBarViewContract.UserAction {

    private var city = MutableLiveData(createCity())
    private var date = MutableLiveData(createDate())
    private var temperature = MutableLiveData(createTemperature())
    private var weatherType = MutableLiveData(Weather.Type.SNOW)

    init {
        // Require remove call
        cityManager.addListener(createCityListener())
        weatherManager.addListener(createWeatherListener())
    }

    override fun getCity(): MutableLiveData<String> {
        return city
    }

    override fun getDate(): MutableLiveData<String> {
        return date
    }

    override fun getTemperature(): MutableLiveData<String> {
        return temperature
    }

    override fun getWeatherType(): MutableLiveData<Weather.Type> {
        return weatherType
    }

    override fun onTemperatureClick() {
        TODO("Not yet implemented")
    }

    override fun onCityClicked() {
        TODO("Not yet implemented")
    }

    override fun onTemperatureUnitClicked(weatherUnit: WeatherUnit) {
        weatherUnitManager.setWeatherUnit(weatherUnit)
    }

    override fun onTemperatureUnitDisplay(metric: WeatherUnit): Boolean {
        return weatherUnitManager.getWeatherUnit() == metric
    }

    private fun createCity(): String {
        return cityManager.getCity()
    }

    private fun createDate(): String {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return ""
        return dateManager.convertTimestampToSpecificFormat1(weather.timestampSecond)
    }

    private fun createTemperature(): String {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return ""
        val temperature = weather.temperature.toInt()
        return "$temperature°"
    }

    private fun createWeatherType(): Weather.Type {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return Weather.Type.SNOW
        return weather.type
    }

    private fun createCityListener() = object : CityManager.Listener {
        override fun onChanged() {
            updateCity()
        }
    }

    private fun createWeatherListener() = object : WeatherManager.Listener {
        override fun onChanged() {
            updateDate()
            updateTemperature()
            updateWeatherType()
        }

        override fun onFailed() {
            TODO("Not yet implemented")
        }
    }

    private fun updateCity() {
        city.value = createCity()
    }

    private fun updateDate() {
        date.value = createDate()
    }

    private fun updateTemperature() {
        temperature.value = createTemperature()
    }

    private fun updateWeatherType() {
        weatherType.value = createWeatherType()
    }
}