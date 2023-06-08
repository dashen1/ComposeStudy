package com.vtech.mobile.compose_weather.weather_api

import java.lang.Exception

class WeatherApiException(
    message: String,
    e: Throwable? = null
) : Exception(
    message,
    e
)