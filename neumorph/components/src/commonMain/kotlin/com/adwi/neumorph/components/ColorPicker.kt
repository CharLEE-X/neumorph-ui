package com.adwi.neumorph.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.composables.picker.HsvColor
import com.adwi.neumorph.components.composables.picker.HueBar
import com.adwi.neumorph.neumorph.LightSource

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
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
) {
    val colorPickerValueState = rememberSaveable(stateSaver = HsvColor.Saver) {
        mutableStateOf(HsvColor.from(color))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HueBar(
            currentColor = colorPickerValueState.value,
            onHueChanged = { newHue ->
                colorPickerValueState.value = colorPickerValueState.value.copy(hue = newHue)
                onColorChanged(colorPickerValueState.value)
            },
            handleColor = handleColor,
            barThickness = barThickness,
            cornerRadius = cornerRadius,
            elevation = elevation,
            lightSource = lightSource,
            lightShadowColor = lightShadowColor,
            darkShadowColor = darkShadowColor,
            modifier = modifier.align(Alignment.Center),
        )
    }
}
