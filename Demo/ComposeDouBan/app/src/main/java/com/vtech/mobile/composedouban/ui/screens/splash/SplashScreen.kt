package com.vtech.mobile.composedouban.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vtech.mobile.composedouban.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SplashScreen(onSplashCompleted: () -> Unit) {
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    // Surface 相当于画布，可以设置边框形状以及 elevation 阴影
    // 使用Scaffold可以实现Material Design 的基本视图界面结构，如侧边应用栏，底部导航栏、导航栏在Scaffold函数下自动布局完成
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            LaunchedEffect(Unit) {
                delay(1000)
                onSplashCompleted()
            }
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.icon_smile),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = getCurrentTime(), color = Color.Black,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "遇见你，真好",
                    color = Color.Black,
                    style = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.mipmap.ic_logo), contentDescription = "Logo")
                Text(
                    text = "豆瓣",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}

private fun getCurrentTime(): String {
    return try {
        val time = System.currentTimeMillis()
        val date = Date(time)
        val format = SimpleDateFormat("yyyy年MM月dd日 EEEE")
        format.format(date)
    } catch (e: Exception) {
        "2023年6月1日 星期一"
    }
}