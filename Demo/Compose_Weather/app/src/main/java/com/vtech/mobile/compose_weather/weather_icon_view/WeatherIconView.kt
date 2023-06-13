package com.vtech.mobile.compose_weather.weather_icon_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.vtech.mobile.compose_weather.R
import com.vtech.mobile.compose_weather.weaher.Weather

@Composable
fun WeatherIconView(
    modifier: Modifier = Modifier,
    weatherType: Weather.Type
) {
    Image(
        painter = painterResource(id = weatherType.toDrawableRes()),
        contentDescription = weatherType.toImageContentDescription(),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

private fun Weather.Type.toImageContentDescription(): String {
    return "Weather is: " + when (this) {
        Weather.Type.CLEAR -> "clear"
        Weather.Type.THUNDERSTORM -> "thunderstorm"
        Weather.Type.DRIZZLE -> "drizzle"
        Weather.Type.CLOUDS -> "clouds"
        Weather.Type.CLOUDS_SCATTERED_CLOUDS -> "scattered clouds"
        Weather.Type.CLOUDS_BROKEN_CLOUDS -> "broken clouds"
        Weather.Type.CLOUDS_FEW_CLOUDS -> "few clouds"
        Weather.Type.RAIN -> "rain"
        Weather.Type.RAIN_FREEZING_RAIN -> "freezing rain"
        Weather.Type.RAIN_VERY_HEAVY_RAIN -> "very heavy rain"
        Weather.Type.RAIN_HEAVY_INTENSITY -> "heavy intensity rain"
        Weather.Type.RAIN_MODERATE_RAIN -> "moderate rain"
        Weather.Type.RAIN_LIGHT_RAIN -> "light rain"
        Weather.Type.SNOW -> "snow"
        Weather.Type.SNOW_LIGHT_SNOW -> "light snow"
        Weather.Type.MIST -> "mist"
    }
}

private fun Weather.Type.toDrawableRes(): Int {
    return when (this) {
        Weather.Type.CLEAR -> R.drawable.weather_sun
        Weather.Type.THUNDERSTORM -> R.drawable.weather_storm
        Weather.Type.DRIZZLE -> R.drawable.weather_2_freezing_drizzle
        Weather.Type.CLOUDS -> R.drawable.weather_2_cloudy
        Weather.Type.CLOUDS_SCATTERED_CLOUDS -> R.drawable.weather_2_partly_cloudy_day
        Weather.Type.CLOUDS_BROKEN_CLOUDS -> R.drawable.weather_2_partly_cloudy_day
        Weather.Type.CLOUDS_FEW_CLOUDS -> R.drawable.weather_2_partly_cloudy_day
        Weather.Type.RAIN -> R.drawable.weather_rain
        Weather.Type.RAIN_FREEZING_RAIN -> R.drawable.weather_sun_cloud_angled_rain
        Weather.Type.RAIN_VERY_HEAVY_RAIN -> R.drawable.weather_sun_cloud_angled_rain
        Weather.Type.RAIN_HEAVY_INTENSITY -> R.drawable.weather_sun_cloud_angled_rain
        Weather.Type.RAIN_MODERATE_RAIN -> R.drawable.weather_sun_cloud_little_rain
        Weather.Type.RAIN_LIGHT_RAIN -> R.drawable.weather_rain
        Weather.Type.SNOW -> R.drawable.weather_mid_snow_fast_winds
        Weather.Type.SNOW_LIGHT_SNOW -> R.drawable.weather_snow
        Weather.Type.MIST -> R.drawable.weather_2_mist
    }
}