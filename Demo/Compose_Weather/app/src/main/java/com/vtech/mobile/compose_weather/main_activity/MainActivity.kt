package com.vtech.mobile.compose_weather.main_activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.main_view.MainView
import com.vtech.mobile.compose_weather.ui.theme.MainTheme

class MainActivity : ComponentActivity() {

    private val userAction by lazy { createUserAction() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                // A surface container using the 'background' color from the theme
                MainView()
            }
        }
        userAction.onCreate(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        userAction.onResume()
    }

    private fun createScreen() = object :MainActivityContract.Screen{
        override fun setStatuesBarTheme(colorRes: Int, themeDark: Boolean) {
            val color = ContextCompat.getColor(this@MainActivity,colorRes)
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
                window.insetsController!!.setSystemBarsAppearance(
                    if (themeDark) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
                window.statusBarColor = color
            }else{
                window.statusBarColor = color
                var flags = window.decorView.systemUiVisibility
                window.decorView.systemUiVisibility = if (themeDark){
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }else{
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }

        override fun setNavigationBarTheme(colorRes: Int, themeDark: Boolean) {
            val color = ContextCompat.getColor(this@MainActivity,colorRes)
            window.navigationBarColor = if (color == Color.WHITE){
                Color.BLACK
            }else{
                color
            }
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
                window.insetsController!!.setSystemBarsAppearance(
                    if(themeDark) 0 else WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                setNavigationBarButtonColor(window,!themeDark)
            }
        }

    }

    private fun setNavigationBarButtonColor(window: Window, darkButotn: Boolean) {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = if (darkButotn){
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }else{
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
    }

    private fun createUserAction():MainActivityContract.UserAction{
        return MainActivityPresenter(
            createScreen(),
            WeatherGraph.getThemeManager(),
            WeatherGraph.getWeatherManager()
        )
    }
}
