package com.adwi.neumorph.android.neumorph.model

import androidx.compose.ui.unit.Dp
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.neumorph.shape.MorphShape

data class NeuAttrs(
    val lightShadowColor: androidx.compose.ui.graphics.Color,
    val darkShadowColor: androidx.compose.ui.graphics.Color,
    val shadowElevation: Dp,
    val shape: MorphShape,
    val lightSource: LightSource = LightSource.LEFT_TOP
)
