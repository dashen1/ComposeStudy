package com.vtech.mobile.compose_weather.weaher

import android.content.Context
import com.vtech.mobile.compose_weather.R
import com.vtech.mobile.compose_weather.city.CityManager
import com.vtech.mobile.compose_weather.date.DateManager
import com.vtech.mobile.compose_weather.ui.theme.ThemeManager
import com.vtech.mobile.compose_weather.weather_api.WeatherApiException
import com.vtech.mobile.compose_weather.weather_api.WeatherApiManager
import com.vtech.mobile.compose_weather.weather_repository.WeatherRepository
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnitManager

class WeatherManagerImpl(
    private val weatherApiManager: WeatherApiManager,
    private val cityManager: CityManager,
    private val dateManager: DateManager,
    private val weatherRepository: WeatherRepository,
    private val weatherUnitManager: WeatherUnitManager,
    private val addOn:AddOn

) : WeatherManager {

    private val listeners = ArrayList<WeatherManager.Listener>()

    override fun load() {
        val city = cityManager.getCity()
        val weatherUnit = weatherUnitManager.getWeatherUnit()
        addOn.postWorkerThread{
            val weather = try {
                weatherApiManager.getWeather(
                    city=city,
                    weatherUnit = weatherUnit
                )
            }catch (e:WeatherApiException){
                null
            }
            val weatherForecastDaily = try {
                weatherApiManager.getWeatherForecastDaily(
                    city = city,
                    weatherUnit = weatherUnit,
                    numberOfDays = 7
                )
            }catch (e:WeatherApiException){
                emptyList()
            }
            addOn.postMainThread{
                val succeeded = weather !=null && weatherForecastDaily.isNotEmpty()
                if (!succeeded){
                    for (listener in listeners) {
                        listener.onChanged()
                    }
                    for (listener in listeners) {
                        listener.onFailed()
                    }
                    return@postMainThread
                }
                weatherRepository.setWeather(weather)
                weatherRepository.setWeatherForecastDaily(weatherForecastDaily)
                for (listener in listeners) {
                    listener.onChanged()
                }
            }
        }
    }

    override fun getWeathers(): List<Weather> {
        val weathers = ArrayList<Weather>()
        val weather = weatherRepository.getWeather()?:return weathers
        weathers.add(weather)
        weathers.addAll(
            weatherRepository.getWeatherForecastDaily().filter {
                dateManager.convertTimestampToSpecificFormat1(it.timestampSecond)!=
                        dateManager.convertTimestampToSpecificFormat1(weather.timestampSecond)
            }
        )
        return weathers
    }

    override fun clearCache() {
        weatherRepository.setWeather(null)
        weatherRepository.setWeatherForecastDaily(emptyList())
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: WeatherManager.Listener) {
        if (listeners.contains(listener)){
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: WeatherManager.Listener) {
        listeners.remove(listener)
    }

    interface AddOn{
         fun postWorkerThread(runnable:Runnable)

         fun postMainThread(runnable: Runnable)
    }
}