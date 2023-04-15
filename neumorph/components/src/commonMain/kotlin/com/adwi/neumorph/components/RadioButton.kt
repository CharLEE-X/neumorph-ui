package com.adwi.neumorph.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.composables.MorphSurface
import com.adwi.neumorph.components.util.Constants.PRESS_SWITCH_DURATION
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.neumorph.neu
import com.adwi.neumorph.neumorph.shape.Oval
import com.adwi.neumorph.neumorph.shape.Pressed

@Composable
fun NeuRadioButton(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: () -> Unit,
    size: Dp = 40.dp,
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    indicatorScale: Float = .8f,
    colors: RadioButtonColors = RadioButtonColors(
        indicatorColor = MaterialTheme.colors.secondary,
        lightShadowColor = null,
        darkShadowColor = null
    ),
) {
    val elevationState by animateDpAsState(
        targetValue = if (value) elevation + 5.dp else elevation,
        animationSpec = tween(PRESS_SWITCH_DURATION)
    )
    val scaleState by animateFloatAsState(
        targetValue = if (value) indicatorScale else 1.2f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    val translationState by animateFloatAsState(
        targetValue = if (value) 0f else 250f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clickable(
                onClick = onValueChange,
                indication = null,
                interactionSource = MutableInteractionSource()
            )
    ) {
        MorphSurface(
            onClick = onValueChange,
            cornerRadius = 0.dp,
            backgroundColor = colors.backgroundColor,
            contentColor = colors.backgroundColor,
            border = null,
            scale = 1f,
            interactionSource = MutableInteractionSource(),
            hasIndication = false,
            content = {},
            modifier = Modifier
                .fillMaxSize()
                .neu(
                    lightShadowColor = colors.lightShadowColor,
                    darkShadowColor = colors.darkShadowColor,
                    shadowElevation = elevationState,
                    lightSource = lightSource,
                    shape = Pressed(Oval)
                )
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                shape = CircleShape,
                color = colors.indicatorColor,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .graphicsLayer {
                        scaleX = scaleState
                        scaleY = scaleState
                        translationX = translationState
                        translationY = translationState
                    },
                content = {}
            )
        }
    }
}

data class RadioButtonColors(
    val backgroundColor: Color = Color.Transparent,
    val indicatorColor: Color,
    val lightShadowColor: Color? = null,
    val darkShadowColor: Color? = null,
)
