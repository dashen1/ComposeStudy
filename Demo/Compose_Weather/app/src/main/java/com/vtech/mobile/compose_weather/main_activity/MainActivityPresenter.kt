package com.vtech.mobile.compose_weather.main_activity

import com.vtech.mobile.compose_weather.R
import com.vtech.mobile.compose_weather.ui.theme.ThemeManager
import com.vtech.mobile.compose_weather.weaher.WeatherManager

class MainActivityPresenter(
    private val screen: MainActivityContract.Screen,
    private val themeManager: ThemeManager,
    private val weatherManager: WeatherManager
) : MainActivityContract.UserAction {

    override fun onCreate(savedInstanceStateNull: Boolean) {
        updateScreen()
        weatherManager.load()
    }

    override fun onResume() {
        weatherManager.load()
    }

    private fun updateScreen() {
        updateTheme()
    }

    private fun updateTheme() {
        val light = themeManager.isLight()
        screen.setStatuesBarTheme(
            if (light) {
                R.color.main_activity_status_bar_light_theme_color
            } else {
                R.color.main_activity_status_bar_dark_theme_color
            },
            themeDark = !light
        )
        screen.setNavigationBarTheme(
            if (light) {
                R.color.main_activity_navigation_bar_light_theme_color
            } else {
                R.color.main_activity_navigation_bar_dark_theme_color
            },
            themeDark = !light
        )
    }

}