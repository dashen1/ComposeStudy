package com.vtech.mobile.compose_weather.weather_repository

import android.content.SharedPreferences
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnit
import org.json.JSONArray
import org.json.JSONObject

class WeatherRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : WeatherRepository {

    private val listeners = ArrayList<WeatherRepository.Listener>()
    private var loaded = false
    private var weather: Weather? = null
    private val weatherForecastDaily = ArrayList<Weather>()

    override fun getWeather(): Weather? {
        loadIfNeeded()
        return weather
    }

    override fun setWeather(weather: Weather?) {
        loadIfNeeded()
        if (this.weather == weather) {
            return
        }
        this.weather = weather
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun getWeatherForecastDaily(): List<Weather> {
        loadIfNeeded()
        return ArrayList(weatherForecastDaily)
    }

    override fun setWeatherForecastDaily(weathers: List<Weather>) {
        loadIfNeeded()
        weatherForecastDaily.clear()
        weatherForecastDaily.addAll(weathers)
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: WeatherRepository.Listener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    override fun removerListener(listener: WeatherRepository.Listener) {
        listeners.remove(listener)
    }

    private fun loadIfNeeded() {
        if (loaded) {
            return
        }

        val weatherJson = sharedPreferences.getString(
            "weather",
            null
        )
        if (weatherJson != null) {
            weather = JSONObject(weatherJson).toWeather()
        }
        weatherForecastDaily.clear()
        weatherForecastDaily.addAll(
            JSONArray(
                sharedPreferences.getString(
                    "weather_forecast_daily",
                    weatherForecastDaily.toJson().toString()
                )
            ).toWeathers()
        )
        loaded = true
    }

    private fun save() {
        val edit = sharedPreferences.edit()
        val weather = weather
        if (weather != null) {
            edit.putString("weather", weather.toJson().toString())
        }
        edit.putString("weather_forecast_daily", weatherForecastDaily.toJson().toString())
            .apply()
    }

    private fun JSONObject.toWeather(): Weather {
        return Weather(
            city = getString("city"),
            weatherUnit = when (getString("weather_unit")) {
                "standard" -> WeatherUnit.STANDARD
                "metric" -> WeatherUnit.METRIC
                "imperial" -> WeatherUnit.IMPERIAL
                else -> throw IllegalStateException("Unknown weather_unit: $this")
            },
            type = when (getString("type")) {
                "clear" -> Weather.Type.CLEAR
                "thunderstorm" -> Weather.Type.THUNDERSTORM
                "drizzle" -> Weather.Type.DRIZZLE
                "clouds" -> Weather.Type.CLOUDS
                "clouds_scattered_clouds" -> Weather.Type.CLOUDS_SCATTERED_CLOUDS
                "clouds_broken_clouds" -> Weather.Type.CLOUDS_BROKEN_CLOUDS
                "clouds_few_clouds" -> Weather.Type.CLOUDS_FEW_CLOUDS
                "rain" -> Weather.Type.RAIN
                "rain_freezing_rain" -> Weather.Type.RAIN_FREEZING_RAIN
                "rain_very_heavy_rain" -> Weather.Type.RAIN_VERY_HEAVY_RAIN
                "rain_heavy_intensity" -> Weather.Type.RAIN_HEAVY_INTENSITY
                "rain_moderate_rain" -> Weather.Type.RAIN_MODERATE_RAIN
                "rain_light_rain" -> Weather.Type.RAIN_LIGHT_RAIN
                "snow" -> Weather.Type.SNOW
                "snow_light_snow" -> Weather.Type.SNOW_LIGHT_SNOW
                "mist" -> Weather.Type.MIST
                else -> throw IllegalStateException("Unknown type: $this")
            },
            temperature = getDouble("temperature").toFloat(),
            humidity = getInt("humidity"),
            pressure = getInt("pressure"),
            timestampSecond = getLong("timestamp_second")
        )
    }

    private fun JSONArray.toWeathers(): List<Weather> {
        val weathers = ArrayList<Weather>()
        for (i in 0 until length()) {
            weathers.add(getJSONObject(i).toWeather())
        }
        return weathers
    }

    private fun List<Weather>.toJson(): JSONArray {
        val jsonArray = JSONArray()
        for (weather in this) {
            jsonArray.put(weather.toJson())
        }
        return jsonArray
    }

    private fun Weather.toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("city", city)
        jsonObject.put(
            "weather_unit",
            when (weatherUnit) {
                WeatherUnit.STANDARD -> "standard"
                WeatherUnit.METRIC -> "metric"
                WeatherUnit.IMPERIAL -> "imperial"
            }
        )
        jsonObject.put(
            "type",
            when (type) {
                Weather.Type.CLEAR -> "clear"
                Weather.Type.THUNDERSTORM -> "thunderstorm"
                Weather.Type.DRIZZLE -> "drizzle"
                Weather.Type.CLOUDS -> "clouds"
                Weather.Type.CLOUDS_SCATTERED_CLOUDS -> "clouds_scattered_clouds"
                Weather.Type.CLOUDS_BROKEN_CLOUDS -> "clouds_broken_clouds"
                Weather.Type.CLOUDS_FEW_CLOUDS -> "clouds_few_clouds"
                Weather.Type.RAIN -> "rain"
                Weather.Type.RAIN_FREEZING_RAIN -> "rain_freezing_rain"
                Weather.Type.RAIN_VERY_HEAVY_RAIN -> "rain_very_heavy_rain"
                Weather.Type.RAIN_HEAVY_INTENSITY -> "rain_heavy_intensity"
                Weather.Type.RAIN_MODERATE_RAIN -> "rain_moderate_rain"
                Weather.Type.RAIN_LIGHT_RAIN -> "rain_light_rain"
                Weather.Type.SNOW -> "snow"
                Weather.Type.SNOW_LIGHT_SNOW -> "snow_light_snow"
                Weather.Type.MIST -> "mist"
            }
        )
        jsonObject.put("temperature", temperature)
        jsonObject.put("humidity", humidity)
        jsonObject.put("pressure", pressure)
        jsonObject.put("timestamp_second", timestampSecond)
        return jsonObject
    }

    companion object{
        const val PREFERENCE_NAME = "weather_repository"
    }
}