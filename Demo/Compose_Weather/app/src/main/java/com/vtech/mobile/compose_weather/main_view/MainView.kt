package com.vtech.mobile.compose_weather.main_view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.main_activity.MainActivityContract
import com.vtech.mobile.compose_weather.main_view_background_view.MainViewBackgroundView
import com.vtech.mobile.compose_weather.weaher.Weather

@Composable
fun MainView(
    preview:Boolean = false
){
    val userAction = Mvp(preview).createUserAction()
    val weathersState = userAction.getWeathers().observeAsState()
    val errorState by userAction.getError().observeAsState()
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        MainViewBackgroundView{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.zIndex(2f)
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                CityEditView
            }
        }
    }
}

private class Mvp(
    private val preview:Boolean
){
    private fun createScreen() = object :MainViewContract.Screen{
    }

    fun createUserAction():MainViewContract.UserAction{
        if (preview){
            return object :MainViewContract.UserAction{
                private var weathers = MutableLiveData(Weather.fakeWeathers)
                private var error = MutableLiveData(false)
                override fun getWeathers(): MutableLiveData<List<Weather>> = weathers

                override fun getError(): MutableLiveData<Boolean> = error
            }
        }
        return MainViewPresenter(
            createScreen(),
            WeatherGraph.getWeatherManager()
        )
    }

}