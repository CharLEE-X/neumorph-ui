@file:OptIn(ExperimentalMaterialApi::class)

package com.adwi.neumorph.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.composables.MorphIcon
import com.adwi.neumorph.neumorph.LightSource

/**
 * Neumorph version of Material Switch
 *
 * @param modifier The companion object Modifier is the empty, default, or starter Modifier that contains no elements.
 * @param value off switch: true or false
 * @param onValueChange sets value
 * @param cornerRadius corner radius applied to all corners
 * @param shape applies shape to component
 * @param elevation size of shadow˚ applied to component
 * @param lightSource defines direction to cast shadow
 * @param backgroundColor color applied behind switch indicator
 * @param switchColor color of switch indicator
 * @param lightShadowColor color of lighter shadow
 * @param darkShadowColor color of darker shadow
 */
@Composable
fun MorphSwitch(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: () -> Unit,
    height: Dp = 100.dp,
    width: Dp = 200.dp,
    cornerRadius: Dp = 30.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    backgroundColor: Color = MaterialTheme.colors.surface,
    switchColor: Color = MaterialTheme.colors.primary,
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
) {
    val switchColorState by animateColorAsState(
        targetValue = if (value) switchColor else MaterialTheme.colors.primaryVariant,
        animationSpec = tween(200),
    )
    val contentColorState by animateColorAsState(
        targetValue = if (value) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        animationSpec = tween(200),
    )
    val translateXState by animateDpAsState(
        targetValue = if (value) ((width / 2) + 10.dp) else 0.dp,
        animationSpec = tween(200),
    )

    Box(
        modifier = modifier
            .height(height)
            .width(width)
            .wrapContentSize()
    ) {
        MorphPressed(
            onClick = onValueChange,
            cornerRadius = cornerRadius,
            backgroundColor = backgroundColor,
            shape = shape,
            lightShadowColor = lightShadowColor,
            darkShadowColor = darkShadowColor,
            elevation = elevation,
            lightSource = lightSource,
            modifier = Modifier,
            content = {}
        )
        RoundedIndicator(
            onValueChange = onValueChange,
            cornerRadius = cornerRadius,
            switchColor = switchColorState,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(.5f)
                .graphicsLayer {
                    translationX = translateXState.value
                }
        ) {
            MorphIcon(
                icon = Icons.Default.Menu,
                tint = contentColorState,
                modifier = Modifier
                    .fillMaxSize(.4f)
                    .rotate(90f)
            )
        }
    }
}

@Composable
fun RoundedIndicator(
    modifier: Modifier = Modifier,
    onValueChange: () -> Unit,
    cornerRadius: Dp = 30.dp,
    switchColor: Color = MaterialTheme.colors.primary,
    content: @Composable () -> Unit,
) {
    Surface(
        color = switchColor,
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp,
        modifier = modifier
            .clickable(
                onClick = onValueChange,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}
