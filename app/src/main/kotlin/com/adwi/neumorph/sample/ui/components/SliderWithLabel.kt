package com.adwi.neumorph.sample.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.MorphSlider
import com.adwi.neumorph.android.components.PreviewTemplate

@Composable
fun SliderWithLabel(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    labelMinWidth: Dp = 24.dp,
    steps: Int = 0,
    labelSize: Int = 35,
    elevation: Dp = 4.dp,
    cornerRadius: Dp = 4.dp,
    backgroundColor: Color = MaterialTheme.colors.background,
    labelColor: Color = MaterialTheme.colors.onBackground,
) {

    Column(modifier = modifier) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 8.dp
            )
            SliderLabel(
                value = value.toInt(),
                backgroundColor = backgroundColor,
                labelColor = labelColor,
                cornerRadius = cornerRadius,
                elevation = elevation,
                modifier = Modifier
                    .padding(start = offset)
                    .size(labelSize.dp)
            )
        }
        MorphSlider(
            value = value,
            onValueChange = onValueChange,
            elevation = elevation,
            cornerRadius = cornerRadius,
            handleColor = MaterialTheme.colors.primary
        )
//        MorphSlider(
//            value = value,
//            onValueChange = onValueChange,
//            valueRange = valueRange,
//            steps = steps,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Slider(
//            value = value,
//            onValueChange = onValueChange,
//            valueRange = valueRange,
//            steps = steps,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsSliderLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        SliderWithLabel(
            value = 5f,
            onValueChange = {},
            valueRange = 0f..20f,
            steps = 10,
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsSliderDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        SliderWithLabel(
            value = 5f,
            onValueChange = {},
            valueRange = 0f..20f,
            steps = 10,
        )
    }
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp,
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}

private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)
