package com.vtech.mobile.composedouban.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.vtech.mobile.composedouban.ui.screens.rank.RankScreen
import com.vtech.mobile.composedouban.ui.theme.ComposeDouBanTheme

class RankDetailActivity:ComponentActivity(){

    companion object{
        fun navigate(context:Context){
            val intent = Intent(context,RankDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent{
            RankDetailPage()
        }
    }
}
@Composable
fun RankDetailPage() {
    ComposeDouBanTheme {
        ProvideWindowInsets {
            RankScreen()
        }
    }
}
