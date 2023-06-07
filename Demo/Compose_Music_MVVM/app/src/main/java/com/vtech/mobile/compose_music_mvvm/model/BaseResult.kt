package com.vtech.mobile.compose_music_mvvm.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
open class BaseResult(private val code: Int? = 0, val message: String? = null) : Serializable {
    open fun resultOk(): Boolean {
        return code == 200
    }
}