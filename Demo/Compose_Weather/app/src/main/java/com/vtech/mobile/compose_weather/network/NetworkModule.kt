package com.vtech.mobile.compose_weather.network

class NetworkModule {

    fun createNetWorkManager():NetworkManager{
        return NetworkManagerImpl()
    }
}