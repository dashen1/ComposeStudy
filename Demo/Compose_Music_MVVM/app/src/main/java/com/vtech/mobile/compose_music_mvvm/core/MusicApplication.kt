package com.vtech.mobile.compose_music_mvvm.core

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Kotlin中不是inner修饰的内部类都是静态的
 * 注意是在类内部使用
 * 最外部的class和java一样，需要new
 */

@HiltAndroidApp
class MusicApplication : Application(){

    companion object{
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}