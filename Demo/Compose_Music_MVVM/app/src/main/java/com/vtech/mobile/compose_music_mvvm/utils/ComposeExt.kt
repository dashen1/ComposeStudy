package com.vtech.mobile.compose_music_mvvm.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.vtech.mobile.compose_music_mvvm.core.AppConfig.APP_DESIGN_WIDTH

/**
 * compose屏幕适配单位
 */
val Number.cdp
    get() = Dp(
        toFloat() *
                Resources.getSystem().displayMetrics.widthPixels
                / APP_DESIGN_WIDTH
                / Resources.getSystem().displayMetrics.density
    )

val Dp.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        Resources.getSystem().displayMetrics
    )

/**
 * compose屏幕适配单位（字体专用）
 */
val Number.csp
    get() = (toFloat() *
            Resources.getSystem().displayMetrics.widthPixels
            / APP_DESIGN_WIDTH /
            Resources.getSystem().displayMetrics.scaledDensity).sp

/**
 * 将数字转换为compose中的dp
 */
val Number.transformDp
    get() = Dp(toFloat() / Resources.getSystem().displayMetrics.density)