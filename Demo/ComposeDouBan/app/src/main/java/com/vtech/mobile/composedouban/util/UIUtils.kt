package com.vtech.mobile.composedouban.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.noRippleClickable(crossinline onclick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }) { onclick() }
}