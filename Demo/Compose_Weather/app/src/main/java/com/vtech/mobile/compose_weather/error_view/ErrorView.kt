package com.vtech.mobile.compose_weather.error_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vtech.mobile.compose_weather.R
import com.vtech.mobile.compose_weather.ui.theme.getTextPrimaryColor
import com.vtech.mobile.compose_weather.ui.theme.getTextSecondaryColor
import com.vtech.mobile.compose_weather.ui.theme.getTextThirdColor

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.error_view_title),
            fontSize = 28.sp,
            color = MaterialTheme.colors.getTextPrimaryColor(),
            fontWeight = FontWeight(900)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            painter = painterResource(id = R.drawable.main_weather_animated_view_figure_7),
            contentDescription = stringResource(id = R.string.error_view_image_content_description),
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(id = R.string.error_view_subtitle),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            color = MaterialTheme.colors.getTextSecondaryColor(),
            fontWeight = FontWeight(700)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.error_view_point_1),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.error_view_point_2),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.error_view_point_3),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600)
        )
    }
}