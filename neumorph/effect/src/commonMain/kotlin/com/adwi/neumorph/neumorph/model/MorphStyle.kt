package com.adwi.neumorph.neumorph.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.adwi.neumorph.neumorph.LightSource

data class MorphStyle(
    val lightShadowColor: Color,
    val darkShadowColor: Color,
    val shadowElevation: Dp,
    val lightSource: LightSource
)
