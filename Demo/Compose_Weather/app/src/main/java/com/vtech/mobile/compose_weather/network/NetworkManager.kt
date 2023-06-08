package com.vtech.mobile.compose_weather.network

import androidx.annotation.WorkerThread

interface NetworkManager {

    @WorkerThread
    fun get(url:String):String?
}