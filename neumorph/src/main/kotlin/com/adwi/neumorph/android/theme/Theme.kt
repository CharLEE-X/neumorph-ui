package com.adwi.neumorph.android.theme

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
    background = AppColors.Dark.BackgroundTop,
    onBackground = AppColors.Dark.TextColor,
    surface = AppColors.Dark.BackgroundBottom,
    onSurface = AppColors.Dark.TextColor
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    onSecondary = Purple700,
    background = AppColors.Light.BackgroundTop,
    onBackground = AppColors.Light.TextColor,
    surface = AppColors.Light.BackgroundBottom,
    onSurface = AppColors.Light.TextColor
)

@Composable
fun MorphUiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
