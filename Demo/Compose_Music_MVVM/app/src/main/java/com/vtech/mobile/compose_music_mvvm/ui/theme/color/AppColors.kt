package com.vtech.mobile.compose_music_mvvm.ui.theme.color

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    statusBar: Color,
    pure: Color,
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    background: Color,
    firstText: Color,
    secondText: Color,
    thirdText: Color,
    firstIcon: Color,
    secondIcon: Color,
    thirdIcon: Color,
    appBarBackground: Color,
    appBarContent: Color,
    card: Color,
    bottomMusicPlayBarBackground: Color,
    divider: Color
) { // internal 声明只能在当前模块使用
    // val 修饰的变量不能有setter
    var statusBarColor: Color by mutableStateOf(statusBar)
        internal set
    var pure : Color by mutableStateOf(pure)
        internal set
    var primary: Color by mutableStateOf(primary)
        internal set
    var primaryVariant: Color by mutableStateOf(primaryVariant)
        internal set
    var secondary: Color by mutableStateOf(secondary)
        internal set
    var background: Color by mutableStateOf(background)
        private set // 访问器私有化 使用了private修饰时，只能手动提供一个公有的函数去修改其属性，就想java中setXXX方法
    var firstText: Color by mutableStateOf(firstText)
        private set
    var secondText: Color by mutableStateOf(secondText)
        private set
    var thirdText: Color by mutableStateOf(thirdText)
        private set
    var firstIcon: Color by mutableStateOf(firstIcon)
        private set
    var secondIcon: Color by mutableStateOf(secondIcon)
        private set
    var thirdIcon: Color by mutableStateOf(thirdIcon)
        private set
    var appBarBackground: Color by mutableStateOf(appBarBackground)
        private set
    var appBarContent: Color by mutableStateOf(appBarContent)
        private set
    var card: Color by mutableStateOf(card)
        private set
    var bottomMusicPlayBarBackground: Color by mutableStateOf(bottomMusicPlayBarBackground)
        private set
    var divider: Color by mutableStateOf(divider)
        private set
}