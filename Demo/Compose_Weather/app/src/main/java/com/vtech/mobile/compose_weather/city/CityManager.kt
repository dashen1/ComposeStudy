package com.vtech.mobile.compose_weather.city

interface CityManager {

    fun getCity():String

    fun setCity(city:String)

    fun addListener(listener:Listener)

    fun removeListener(listener:Listener)

    interface Listener{
        fun onChanged()
    }
}