package com.adwi.neumorph.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
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
        onClick = onClick,
        shape = shape,
        contentColor = contentColor,
        border = border,
        color = Color.Transparent,
        interactionSource = interactionSource,
        indication = if (hasIndication) LocalIndication.current else null,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
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

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun TestButtonLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        MorphSurface(
            modifier = Modifier,
            onClick = {},
            cornerRadius = 10.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onSurface,
            border = null,
            scale = 1f,
            invertedBackgroundColors = false,
            hasIndication = false,
            interactionSource = MutableInteractionSource(),
            content = { color ->
                Text(text = "Button",
                    modifier = Modifier.padding(12.dp),
                    color = color)
            }
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun TestButtonDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        MorphSurface(
            modifier = Modifier,
            onClick = {},
            cornerRadius = 10.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            border = null,
            scale = 1f,
            invertedBackgroundColors = true,
            hasIndication = false,
            interactionSource = MutableInteractionSource(),
            content = { color ->
                Text(text = "Button",
                    modifier = Modifier.padding(12.dp),
                    color = color)
            }
        )

    }
}
