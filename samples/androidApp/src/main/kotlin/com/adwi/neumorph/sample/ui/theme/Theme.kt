package com.adwi.neumorph.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    onPrimary = Color.Black,
    secondary = Teal200,
    onSecondary = Purple700,
    background = NeuColors.Dark.BackgroundTop,
    onBackground = NeuColors.Dark.TextColor,
    surface = NeuColors.Dark.BackgroundBottom,
    onSurface = NeuColors.Dark.TextColor
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    onSecondary = Purple700,
    background = NeuColors.Light.BackgroundTop,
    onBackground = NeuColors.Light.TextColor,
    surface = NeuColors.Light.BackgroundBottom,
    onSurface = NeuColors.Light.TextColor
)

@Composable
fun NeuTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
