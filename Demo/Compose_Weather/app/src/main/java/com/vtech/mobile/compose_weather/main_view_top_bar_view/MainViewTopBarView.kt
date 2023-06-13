package com.vtech.mobile.compose_weather.main_view_top_bar_view

import android.widget.Space
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.neumorphism_card_square_view.NeumorphismCardSquareView
import com.vtech.mobile.compose_weather.ui.theme.*
import com.vtech.mobile.compose_weather.weaher.Weather
import com.vtech.mobile.compose_weather.weather_unit.WeatherUnit

@Composable
fun MainViewTopBarView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val textColor = MaterialTheme.colors.getMainViewTopBarViewTextColor()
    val userAction = Mvp(preview).createUserAction()
    val cityState by userAction.getCity().observeAsState()
    val dateState by userAction.getDate().observeAsState()
    val temperatureState by userAction.getTemperature().observeAsState()
    val weatherTypeState by userAction.getWeatherType().observeAsState()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .align(Alignment.Top)
                .padding(start = 24.dp, top = 20.dp, end = 24.dp)
                .clickable { userAction.onCityClicked() }
        ) {
            Text(
                text = cityState!!,
                fontSize = 22.sp,
                color = textColor,
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
            Text(
                text = dateState!!,
                fontSize = 22.sp,
                color = MaterialTheme.colors.getMainViewTopBarViewDateTextColor(weatherType = weatherTypeState!!),
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
        }
        var temperatureContainerStatus by remember {
            mutableStateOf(
                if (preview) TemperatureContainerStatus.EXPANDED else TemperatureContainerStatus.COLLAPSED
            )
        }
        val temperatureContainerTransition =
            updateTransition(targetState = temperatureContainerStatus)
        val temperatureContainerWidth by temperatureContainerTransition.animateDp {
            when (it) {
                TemperatureContainerStatus.COLLAPSED -> 130.dp
                TemperatureContainerStatus.EXPANDED -> 200.dp
            }
        }
        val temperatureContainerHeight by temperatureContainerTransition.animateDp {
            when (it) {
                TemperatureContainerStatus.EXPANDED -> 110.dp
                TemperatureContainerStatus.COLLAPSED -> 250.dp
            }
        }
        NeumorphismCardSquareView(
            modifier = Modifier
                .width(temperatureContainerWidth)
                .height(temperatureContainerHeight),
            backgroundColor = MaterialTheme.colors.getMainViewTopViewCardColor(),
            onclick = {
                userAction.onTemperatureClick()
                temperatureContainerStatus = when (temperatureContainerStatus) {
                    TemperatureContainerStatus.COLLAPSED -> TemperatureContainerStatus.EXPANDED
                    TemperatureContainerStatus.EXPANDED -> TemperatureContainerStatus.COLLAPSED
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(110.dp)
                    .align(Alignment.TopEnd)
                    .zIndex(2f)
            ) {
                Text(
                    text = temperatureState!!,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight(900),
                    color = textColor
                )
                if (temperatureState == "") {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .align(Alignment.Center)
                            .zIndex(2f),
                        color = textColor
                    )
                }
            }
            val temperatureUnityAlpha by temperatureContainerTransition.animateFloat {
                when (it) {
                    TemperatureContainerStatus.COLLAPSED -> 0f
                    TemperatureContainerStatus.EXPANDED -> 1f
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .alpha(temperatureUnityAlpha)
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                Text(
                    text = "Temperature",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = textColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.clickable {
                    userAction.onTemperatureUnitClicked(WeatherUnit.STANDARD)
                }) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.STANDARD),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.STANDARD)
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Standard",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
                Row(
                    modifier = Modifier.clickable {
                        userAction.onTemperatureUnitClicked(WeatherUnit.METRIC)
                    }
                ) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.METRIC),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.METRIC)
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Metric",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.clickable {
                    userAction.onTemperatureUnitClicked(WeatherUnit.IMPERIAL)
                }) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.IMPERIAL),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.IMPERIAL)
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Imperial",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewLightPreview() {
    MainTheme {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewDarkPreview() {
    MainTheme {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

private enum class TemperatureContainerStatus {
    COLLAPSED,
    EXPANDED
}

class Mvp(
    private val preview: Boolean
) {
    private fun createScreen() = object : MainViewTopBarViewContract.Screen {
        // todo
    }

    fun createUserAction(): MainViewTopBarViewContract.UserAction {
        if (preview) {
            return object : MainViewTopBarViewContract.UserAction {
                private var city = MutableLiveData("Paris,France")
                private var date = MutableLiveData("Mon 18")
                private var temperature = MutableLiveData("28")
                private var weatherType = MutableLiveData(Weather.Type.SNOW)
                override fun getCity(): MutableLiveData<String> = city

                override fun getDate(): MutableLiveData<String> = date

                override fun getTemperature(): MutableLiveData<String> = temperature

                override fun getWeatherType(): MutableLiveData<Weather.Type> = weatherType

                override fun onTemperatureClick() {
                    TODO("Not yet implemented")
                }

                override fun onCityClicked() {
                    TODO("Not yet implemented")
                }

                override fun onTemperatureUnitClicked(weatherUnit: WeatherUnit) {
                    TODO("Not yet implemented")
                }

                override fun onTemperatureUnitDisplay(metric: WeatherUnit): Boolean = false
            }
        }
        return MainViewTopBarViewPresenter(
            createScreen(),
            WeatherGraph.getCityManager(),
            WeatherGraph.getDateManager(),
            WeatherGraph.getWeatherManager(),
            WeatherGraph.getWeatherUnitManager()
        )
    }
}
