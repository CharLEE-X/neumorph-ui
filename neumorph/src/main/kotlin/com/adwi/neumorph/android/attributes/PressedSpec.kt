package com.adwi.neumorph.android.attributes

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.util.Constants.PRESS_BUTTON_DURATION

@Composable
internal fun PressedSpec(
    isPressed: Boolean,
    duration: Int = PRESS_BUTTON_DURATION,
    elevation: Dp,
    cornerRadius: Dp,
    content: @Composable (
        elevationState: Dp,
        cornersState: Dp,
        scaleState: Float
    ) -> Unit
) {
    val elevationState by animateDpAsState(
        targetValue = if (isPressed) elevation else -elevation,
        animationSpec = tween(duration)
    )
    val cornerRadiusState by animateDpAsState(
        targetValue = if (isPressed) (cornerRadius.value.toInt() * .95).dp else cornerRadius,
        animationSpec = tween(duration)
    )
    val scaleState by animateFloatAsState(
        targetValue = if (isPressed) .97f else 1f,
        animationSpec = tween(duration)
    )

    CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
        content(elevationState, cornerRadiusState, scaleState)
    }
}
