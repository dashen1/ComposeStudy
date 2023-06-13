package com.vtech.mobile.composedouban

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.vtech.mobile.composedouban.ui.screens.home.HomeScreen
import com.vtech.mobile.composedouban.ui.screens.splash.SplashScreen
import com.vtech.mobile.composedouban.ui.theme.ComposeDouBanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeDouBanUI()
        }
    }
}

enum class AppState {
    Splash,
    Home
}

@Composable
fun ComposeDouBanUI() {
    ComposeDouBanTheme {
        ProvideWindowInsets {
            val (appState, setAppState) = remember {
                mutableStateOf(AppState.Splash)
            }

            when (appState) {
                AppState.Splash -> SplashScreen { setAppState(AppState.Home) }
                AppState.Home -> HomeScreen()
            }
        }
    }
}

