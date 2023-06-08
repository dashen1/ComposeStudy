package com.vtech.mobile.compose_weather.ui.theme

import android.content.Context
import com.vtech.mobile.compose_weather.R

class ThemeModuleImpl(
    private val context: Context
) : ThemeManager {

    override fun isLight(): Boolean {
        return context.resources.getBoolean(R.bool.theme_bool_light_mode)
    }
}