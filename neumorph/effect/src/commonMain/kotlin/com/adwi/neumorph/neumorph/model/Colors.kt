package com.adwi.neumorph.neumorph.model

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object DefaultColors {
    object Light {
        val BackgroundTop = Color(0XFFEEF0F5)
        val BackgroundBottom = Color(0xFFFFFFFF)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFA8B5C7)
    }

    object Dark {
        val BackgroundTop = Color(0XFF394253)
        val BackgroundBottom = Color(0XFF181C27)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
    }

    @Composable
    fun lightShadow() = if (MaterialTheme.colors.isLight)
        Light.LightShadow else Dark.LightShadow

    @Composable
    fun darkShadow() = if (MaterialTheme.colors.isLight)
        Light.DarkShadow else Dark.DarkShadow
}

@Composable
fun defaultGradientColors() = listOf(
    getTopBackgroundColor(),
    getBottomBackgroundColor()
)

@Composable
private fun getTopBackgroundColor(): Color = if (!isSystemInDarkTheme())
    DefaultColors.Light.BackgroundTop else DefaultColors.Dark.BackgroundTop

@Composable
private fun getBottomBackgroundColor(): Color = if (!isSystemInDarkTheme())
    DefaultColors.Light.BackgroundBottom else DefaultColors.Dark.BackgroundBottom
