package com.adwi.neumorph.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

/**
 * Neumorph version of Material Radio Button
 *
 * @param modifier The companion object Modifier is the empty, default, or starter Modifier that contains no elements.
 * @param value off switch: true or false
 * @param onValueChange sets value
 * @param cornerRadius corner radius applied to all corners
 * @param shape applies shape to component
 * @param elevation size of shadow applied to component
 * @param lightSource defines direction to cast shadow
 * @param backgroundColor color applied behind Radio Button indicator
 * @param switchColor color of Radio Button indicator
 * @param lightShadowColor color of lighter shadow
 * @param darkShadowColor color of darker shadow
 */
@Composable
fun MorphRadioButton(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: () -> Unit,
    elevation: Dp = 3.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    backgroundColor: Color = Color.Transparent,
    radioColor: Color = MaterialTheme.colors.primary,
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
) {
    val elevationState by animateDpAsState(
        targetValue = if (value) elevation + 5.dp else elevation,
        animationSpec = tween(PRESS_SWITCH_DURATION)
    )
    val scaleState by animateFloatAsState(
        targetValue = if (value) .8f else 1.2f,
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

    MorphSurface(
        onClick = onValueChange,
        cornerRadius = 0.dp,
        backgroundColor = backgroundColor,
        contentColor = Color.Transparent,
        border = null,
        scale = 1f,
        interactionSource = MutableInteractionSource(),
        hasIndication = false,
        modifier = modifier
            .neu(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                shadowElevation = elevationState,
                lightSource = lightSource,
                shape = Pressed(Oval)
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                shape = CircleShape,
                color = radioColor,
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
