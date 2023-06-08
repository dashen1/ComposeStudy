package com.vtech.mobile.compose_weather.main_activity

import androidx.annotation.ColorRes

interface MainActivityContract {

    interface UserAction{

        fun onCreate(savedInstanceStateNull:Boolean)

        fun onResume()
    }

    interface Screen{

        fun setStatuesBarTheme(@ColorRes colorRes: Int,themeDark:Boolean)

        fun setNavigationBarTheme(@ColorRes colorRes:Int,themeDark: Boolean )
    }
}