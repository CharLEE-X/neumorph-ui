@file:OptIn(ExperimentalMaterialApi::class)

package com.adwi.neumorph.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import com.adwi.neumorph.components.composables.NeuIcon
import com.adwi.neumorph.neumorph.LightSource

@Composable
fun NeuSwitch(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: () -> Unit,
    height: Dp = 40.dp,
    width: Dp = 80.dp,
    cornerRadius: Dp = 20.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    colors: SwitchColors = SwitchColors(
        backgroundColor = MaterialTheme.colors.surface,
        indicatorOnColor = MaterialTheme.colors.primary,
        indicatorOffColor = MaterialTheme.colors.secondary,
        indicatorIconOnColor = MaterialTheme.colors.onPrimary,
        indicatorIconOffColor = MaterialTheme.colors.onSecondary,
    ),
) {
    val switchColorState by animateColorAsState(
        targetValue = if (value) colors.indicatorOnColor else colors.indicatorOffColor,
        animationSpec = tween(200),
    )
    val contentColorState by animateColorAsState(
        targetValue = if (value) colors.indicatorIconOnColor else colors.indicatorIconOffColor,
        animationSpec = tween(200),
    )
    val translateXState by animateDpAsState(
        targetValue = if (value) (width + cornerRadius + elevation) else -elevation,
        animationSpec = spring(
            stiffness = 500f,
            dampingRatio = 0.6f
        ),
    )

    Box(
        modifier = modifier
            .height(height)
            .width(width)
            .wrapContentSize()
    ) {
        NeuPressed(
            onClick = onValueChange,
            cornerRadius = cornerRadius,
            backgroundColor = colors.backgroundColor,
            shape = shape,
            lightShadowColor = colors.lightShadowColor,
            darkShadowColor = colors.darkShadowColor,
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
            NeuIcon(
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

data class SwitchColors(
    val backgroundColor: Color,
    val indicatorOnColor: Color,
    val indicatorOffColor: Color,
    val indicatorIconOnColor: Color,
    val indicatorIconOffColor: Color,
    val lightShadowColor: Color? = null,
    val darkShadowColor: Color? = null,
)
