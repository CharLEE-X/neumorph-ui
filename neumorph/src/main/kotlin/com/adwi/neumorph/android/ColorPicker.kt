package com.adwi.neumorph.android

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.picker.HsvColor
import com.adwi.neumorph.android.picker.HueBar
import com.adwi.neumorph.android.theme.AppColors

@Composable
fun MorphColorPicker(
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    onColorChanged: (HsvColor) -> Unit,
    barThickness: Dp = 30.dp,
    cornerRadius: Dp = 60.dp,
    elevation: Dp = (10).dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    handleColor: Color = MaterialTheme.colors.primary,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
) {
    val colorPickerValueState = rememberSaveable(stateSaver = HsvColor.Saver) {
        mutableStateOf(HsvColor.from(color))
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scaleState by animateFloatAsState(
        targetValue = if (isPressed) .98f else 1f,
        animationSpec = TweenSpec(200)
    )
    val elevationState by animateDpAsState(
        targetValue = if (isPressed) elevation / 2 else elevation,
        animationSpec = TweenSpec(200)
    )

    MorphPunched (
        cornerRadius = cornerRadius,
        elevation = elevationState,
        lightShadowColor = lightShadowColor,
        darkShadowColor = darkShadowColor,
        lightSource = lightSource,
        modifier = modifier
            .fillMaxWidth()
            .height(barThickness)
            .graphicsLayer {
                scaleX = scaleState
                scaleY = scaleState
            }
    ) {
        HueBar(
            currentColor = colorPickerValueState.value,
            onHueChanged = { newHue ->
                colorPickerValueState.value = colorPickerValueState.value.copy(hue = newHue)
                onColorChanged(colorPickerValueState.value)
            },
            handleColor = handleColor,
            modifier = Modifier,
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun ColorPickerLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        var color by remember { mutableStateOf(Color.Green) }
        MorphColorPicker(
            color = Color.Green,
            onColorChanged = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun ColorPickerDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        MorphColorPicker(
            color = Color.Green,
            onColorChanged = {}
        )
    }
}