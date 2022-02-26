package com.adwi.neumorph.android.neumorph.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.neumorph.shape.CornerShape
import com.adwi.neumorph.android.neumorph.shape.Pressed
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.neumorph.shape.RoundedCorner
import com.adwi.neumorph.android.theme.AppColors

internal val defaultElevation = 6.dp
internal val defaultCornerShape: CornerShape = RoundedCorner(12.dp)

@Composable
fun defaultPressedAttrs() = NeuAttrs(
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Pressed(defaultCornerShape),
)

@Composable
fun defaultPunchedAttrs() = NeuAttrs(
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Punched(defaultCornerShape)
)
