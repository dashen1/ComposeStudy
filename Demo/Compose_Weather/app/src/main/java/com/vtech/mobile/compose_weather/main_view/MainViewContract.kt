package com.vtech.mobile.compose_weather.main_view

import androidx.lifecycle.MutableLiveData
import com.vtech.mobile.compose_weather.weaher.Weather

interface MainViewContract {

    interface UserAction{
        fun getWeathers():MutableLiveData<List<Weather>>

        fun getError():MutableLiveData<Boolean>
    }

    interface Screen
}