package com.vtech.mobile.compose_weather.city_edit_view

import com.vtech.mobile.compose_weather.city.CityManager
import java.util.*

class CityEditViewPresenter(
    private val screen: CityEditViewContract.Screen,
    private val cityManager: CityManager

) : CityEditViewContract.UserAction {
    override fun onCityValidated(text: String) {

        cityManager.setCity(text.capitalize(Locale.getDefault()))
    }

    override fun onInfoClicked() {
    }


}