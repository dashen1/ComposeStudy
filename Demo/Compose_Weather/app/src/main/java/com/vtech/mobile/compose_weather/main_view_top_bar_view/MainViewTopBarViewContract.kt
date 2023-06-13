package com.vtech.mobile.compose_weather.main_view_top_bar_view

import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnit

interface MainViewTopBarViewContract {

    interface UserAction{
         fun getCity():MutableLiveData<String>

         fun getDate():MutableLiveData<String>

         fun getTemperature():MutableLiveData<String>

         fun getWeatherType():MutableLiveData<Weather.Type>

         fun onTemperatureClick()

         fun onCityClicked()

         fun onTemperatureUnitClicked(weatherUnit: WeatherUnit)

        /**
         * True is current
         */
        fun onTemperatureUnitDisplay(metric:WeatherUnit):Boolean
    }

    interface Screen
}