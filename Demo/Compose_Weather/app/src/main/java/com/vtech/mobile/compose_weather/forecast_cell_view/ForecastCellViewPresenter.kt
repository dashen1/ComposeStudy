package com.vtech.mobile.compose_weather.forecast_cell_view

import com.vtech.mobile.compose_weather.date.DateManager
import com.vtech.mobile.compose_weather.weaher.Weather

class ForecastCellViewPresenter(
    private val screen: ForecastCellViewContract.Screen,
    private val weather: Weather?,
    private val dateManager: DateManager
) : ForecastCellViewContract.UserAction {
    override fun onDisplayTextTemperature(): String {
        return createTemperatureToDisplay()
    }

    private fun createTemperatureToDisplay(): String {
        val weather = weather ?: return ""
        return dateManager.convertTimestampToSpecificFormat1(weather.timestampSecond)
    }
}