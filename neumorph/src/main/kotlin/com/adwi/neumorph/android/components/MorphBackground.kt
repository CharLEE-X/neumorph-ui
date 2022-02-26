package com.adwi.neumorph.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.theme.AppColors

@Composable
fun MorphBackground(
    modifier: Modifier = Modifier,
    inverted: Boolean = false,
    cornerRadius: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    gradient: List<Color> = gradientColors(),
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
private fun getTopBackgroundColor(): Color {
    return if (!isSystemInDarkTheme()) {
        AppColors.Light.BackgroundTop
    } else {
        AppColors.Dark.BackgroundTop
    }
}

@Composable
private fun getBottomBackgroundColor(): Color {
    return if (!isSystemInDarkTheme()) {
        AppColors.Light.BackgroundBottom
    } else {
        AppColors.Dark.BackgroundBottom
    }
}

@Composable
fun customGradientColors(color: Color) = listOf(
    color,
    color.copy(alpha = .8f)
)

@Composable
fun transparentColors() = listOf(Color.Transparent, Color.Transparent)

@Composable
fun gradientColors() = listOf(
    getTopBackgroundColor(),
    getBottomBackgroundColor()
)

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun MorphBackgroundLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        MorphBackground() {}
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun MorphBackgroundDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        MorphBackground() {}
    }
}
