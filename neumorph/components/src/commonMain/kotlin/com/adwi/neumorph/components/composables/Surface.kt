package com.adwi.neumorph.components.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

@Composable
fun MorphSurface(
    modifier: Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface,
    border: BorderStroke? = null,
    scale: Float,
    invertedBackgroundColors: Boolean = false,
    hasIndication: Boolean,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    content: @Composable (contentColor: Color) -> Unit,
) {
    Surface(
        shape = shape,
        contentColor = contentColor,
        border = border,
        color = Color.Transparent,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = if (hasIndication) LocalIndication.current else null,
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (backgroundColor == Color.Transparent) {
                            transparentColors()
                        } else {
                            if (invertedBackgroundColors)
                                customGradientColors(color = backgroundColor).reversed()
                            else customGradientColors(color = backgroundColor)
                        }

                    ),
                    shape = shape
                )
        ) {
            content(contentColor)
        }
    }
}
