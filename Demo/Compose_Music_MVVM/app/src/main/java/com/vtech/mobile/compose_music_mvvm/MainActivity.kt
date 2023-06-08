package com.vtech.mobile.compose_music_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vtech.mobile.compose_music_mvvm.ui.theme.APPTheme
import com.vtech.mobile.compose_music_mvvm.ui.theme.Compose_Music_MVVMTheme
import com.vtech.mobile.compose_music_mvvm.ui.theme.themeTypeState
import com.vtech.mobile.compose_music_mvvm.utils.setAndroidNativeLightStatusBar
import com.vtech.mobile.compose_music_mvvm.utils.transformDp
import com.vtech.mobile.compose_music_mvvm.utils.transparentStatusBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setAndroidNativeLightStatusBar()
        setContent {
            APPTheme(themeTypeState.value) {
                // A surface container using the 'background' color from the theme
               val navController = rememberAnimatedNavController()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = LocalWindowInsets.current.navigationBars.bottom.transformDp)
                ) {

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    Compose_Music_MVVMTheme {
//        Greeting("Android")
//    }
}

