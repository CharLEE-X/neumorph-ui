package com.adwi.neumorph.components.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.neumorph.model.defaultGradientColors

@Composable
fun MorphBackground(
    modifier: Modifier = Modifier,
    inverted: Boolean = false,
    cornerRadius: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    gradient: List<Color> = defaultGradientColors(),
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        shape = shape,
        color = Color.Transparent,
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = if (inverted) gradient.reversed() else gradient
                )
            )
            .clip(shape)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun customGradientColors(color: Color) = listOf(
    color,
    color.copy(alpha = .8f)
)

@Composable
fun transparentColors() = listOf(Color.Transparent, Color.Transparent)
