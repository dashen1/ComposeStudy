package com.vtech.mobile.compose_weather.main_view_background_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.vtech.mobile.compose_weather.ui.theme.getMainViewBottomBackgroundColor
import com.vtech.mobile.compose_weather.ui.theme.getMainViewTopBackgroundColor

@Composable
fun MainViewBackgroundView(
    modifier: Modifier=Modifier,
    content:@Composable BoxScope.()->Unit
){
    Box(modifier = modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colors.getMainViewTopBackgroundColor(),
                MaterialTheme.colors.getMainViewBottomBackgroundColor()
            )
        )
    )){
        content()
    }
}