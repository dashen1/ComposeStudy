package com.vtech.mobile.composechat

import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowCompat
import com.vtech.mobile.composechat.theme.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val drawerOpen by viewModel.drawerShouldBeOpened
                        .collectAsState()
                    if (drawerOpen){
                        LaunchedEffect(Unit){
                            try {
                                drawerState.open()
                            }finally {
                                viewModel.resetOpenDrawerAction()
                            }
                        }
                    }
                    val scope = rememberCoroutineScope()
                    if (drawerState.isOpen){
                        BackHandler {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }


                }
            }
        )
    }
}