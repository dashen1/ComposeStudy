package com.vtech.mobile.compose_weather.forecast_cell_view

import android.animation.AnimatorInflater
import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.ui.theme.getForecastViewCardColor
import com.vtech.mobile.compose_weather.ui.theme.getTextPrimaryColor
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weather_icon_view.WeatherIconView
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType
import soup.neumorphism.R

@Composable
fun ForecastCellView(
    weather: Weather?,
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val userAction = Mvp(preview = preview, weather = weather).createUserAction()
    Column(modifier = modifier.height(150.dp)) {
        Box(
            modifier = modifier
                .height(130.dp)
        ) {
            val light = MaterialTheme.colors.isLight
            val topStartShadowColor = if (light) "#C6CEDA" else "#101010"
            val bottomEndShadowColor = if (light) "#FEFEFF" else "#262C37"
            val forecastViewCardColor = MaterialTheme.colors.getForecastViewCardColor()
            AndroidView(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                factory = { context ->
                    NeumorphImageButton(context).apply {
                        setShadowColorLight(Color.parseColor(bottomEndShadowColor))
                        setShadowColorDark(Color.parseColor(topStartShadowColor))
                        setShapeType(ShapeType.DEFAULT)
                        setBackgroundColor(
                            argb(
                                forecastViewCardColor.alpha,
                                forecastViewCardColor.red,
                                forecastViewCardColor.green,
                                forecastViewCardColor.blue
                            )
                        )
                        if (!preview) {
                            stateListAnimator = AnimatorInflater.loadStateListAnimator(
                                context,
                                R.animator.button_state_list_anim_neumorph
                            )
                        }
                        setOnClickListener {
                        }
                    }
                }
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(14.dp)
            ) {
                if (weather == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .align(Alignment.Center)
                                .zIndex(2f),
                            color = MaterialTheme.colors.getTextPrimaryColor()
                        )
                    }
                } else {
                    WeatherIconView(
                        weatherType = weather.type,
                        modifier = Modifier.weight(2.2f)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${weather.temperature.toInt()}°",
                            fontSize = 19.sp,
                            fontWeight = FontWeight(900),
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(bottom = 4.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (weather != null) {
                // To put in presenter the text creation
                Text(
                    text = userAction.onDisplayTextTemperature(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(900),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = 4.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

private fun argb(alpha: Float, red: Float, green: Float, blue: Float): Int {
    return (alpha * 255.0f + 0.5f).toInt() shl 24 or
            ((red * 255.0f + 0.5f).toInt() shl 16) or
            ((green * 255.0f + 0.5f).toInt() shl 8) or
            (blue * 255.0f + 0.5f).toInt()
}

private class Mvp(
    private val preview: Boolean,
    private val weather: Weather?
) {
    private fun createScreen() = object : ForecastCellViewContract.Screen {
    }

    fun createUserAction(): ForecastCellViewContract.UserAction {
        if (preview) {
            return object : ForecastCellViewContract.UserAction {
                override fun onDisplayTextTemperature(): String = "28"
            }
        }
        return ForecastCellViewPresenter(
            createScreen(),
            weather,
            WeatherGraph.getDateManager()
        )
    }
}