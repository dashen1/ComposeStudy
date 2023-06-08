package com.vtech.mobile.compose_weather.city

import android.content.SharedPreferences

class CityManagerImpl (
    private val sharedPreferences:SharedPreferences
    ):CityManager{

    private val listeners = ArrayList<CityManager.Listener>()
    private var city = "Paris,France"
    private var loaded = false

    override fun getCity(): String {
        loadIfNeeded()
        return city
    }

    override fun setCity(city: String) {
        loadIfNeeded()
        if (this.city==city){
            return
        }
        this.city = city
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: CityManager.Listener) {
        if (listeners.contains(listener)){
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: CityManager.Listener) {
        listeners.remove(listener)
    }

    private fun loadIfNeeded(){
        if (loaded){
            return
        }
        city = sharedPreferences.getString("city",city)?:"city"
        loaded = true
    }

    private fun save(){
        sharedPreferences.edit().putString("city",city).apply()
    }

    companion object{
        const val PREFERENCE_NAME = "weather_current_city"
    }
}