package com.vtech.mobile.compose_weather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFFDE807),
    primaryVariant = Color(0xFFFDE807),
    secondary = Color(0xFF30B0D3),
    background = Color(0xFF0B222F)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF0B222F),
    primaryVariant = Color(0xFF0B222F),
    secondary = Color(0xFF30B0D3),
    background = Color(0xFFEEF0F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)
