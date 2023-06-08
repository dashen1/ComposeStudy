package com.vtech.mobile.compose_weather.ui.theme

import com.vtech.mobile.compose_weather.graph.WeatherGraph

class ThemeModule {

    fun createThemeManager(): ThemeManager {
        return ThemeModuleImpl(WeatherGraph.getContext())
    }
}