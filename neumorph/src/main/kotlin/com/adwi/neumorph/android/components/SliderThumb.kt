package com.adwi.neumorph.android.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.MorphPunched
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.theme.AppColors

@Composable
fun MorphSliderThumb(
    modifier: Modifier = Modifier,
    offset: Dp,
    size: Dp = 20.dp,
    cornerRadius: Dp = 30.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    elevation: Dp = 10.dp,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    lightSource: LightSource = LightSource.LEFT_TOP,
    handleColor: Color = MaterialTheme.colors.primary,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    content: @Composable () -> Unit,
) {
    MorphPunched(
        onClick = {},
        cornerRadius = cornerRadius,
        elevation = elevation,
        shape = shape,
        backgroundColor = handleColor,
        lightShadowColor = lightShadowColor,
        darkShadowColor = darkShadowColor,
        interactionSource = interactionSource,
        lightSource = lightSource,
        modifier = modifier
            .size(size)
            .offset(x = offset),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun SliderThumbLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        MorphSliderThumb(offset = 0.dp, content = {})
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun SliderThumbDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        MorphSliderThumb(offset = 0.dp, content = {})
    }
}