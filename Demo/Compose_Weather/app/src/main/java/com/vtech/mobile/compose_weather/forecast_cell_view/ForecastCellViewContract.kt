package com.vtech.mobile.compose_weather.forecast_cell_view

interface ForecastCellViewContract {

    interface UserAction{
        fun onDisplayTextTemperature():String
    }

    interface Screen
}