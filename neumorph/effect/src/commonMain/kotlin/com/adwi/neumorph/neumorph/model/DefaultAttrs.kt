package com.adwi.neumorph.neumorph.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.neumorph.shape.CornerShape
import com.adwi.neumorph.neumorph.shape.Pressed
import com.adwi.neumorph.neumorph.shape.Punched
import com.adwi.neumorph.neumorph.shape.RoundedCorner

internal val defaultElevation = 6.dp
internal val defaultCornerShape: CornerShape = RoundedCorner(12.dp)

@Composable
fun defaultPressedAttrs() = NeuAttrs(
    lightShadowColor = DefaultColors.lightShadow(),
    darkShadowColor = DefaultColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Pressed(defaultCornerShape),
)

@Composable
fun defaultPunchedAttrs() = NeuAttrs(
    lightShadowColor = DefaultColors.lightShadow(),
    darkShadowColor = DefaultColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Punched(defaultCornerShape)
)
