package com.adwi.neumorph.neumorph.model

import androidx.compose.ui.unit.Dp
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.neumorph.shape.MorphShape

data class NeuAttrs(
    val lightShadowColor: androidx.compose.ui.graphics.Color,
    val darkShadowColor: androidx.compose.ui.graphics.Color,
    val shadowElevation: Dp,
    val shape: MorphShape,
    val lightSource: LightSource = LightSource.LEFT_TOP
)
