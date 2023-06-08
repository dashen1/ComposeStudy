package com.vtech.mobile.compose_weather.city_edit_view

interface CityEditViewContract {

    interface UserAction{

        fun onCityValidated(text:String)

        fun onInfoClicked()
    }

    interface Screen
}