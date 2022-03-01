package com.adwi.neumorph.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun ColorPickerLight() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        val colorValue = remember { mutableStateOf(Color.Green) }
        MorphColorPicker(
            color = colorValue.value,
            onColorChanged = { hsvColor: HsvColor ->
                colorValue.value = hsvColor.toColor()
            },
            elevation = 20.dp,
            cornerRadius = 30.dp,
            handleColor = Color.DarkGray,
            modifier = Modifier.height(30.dp)
        )
    }
}
