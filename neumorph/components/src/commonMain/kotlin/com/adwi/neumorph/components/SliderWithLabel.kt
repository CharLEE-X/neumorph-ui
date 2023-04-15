package com.adwi.neumorph.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.sample.ui.components.SliderLabel

@Composable
fun MorphSlider(
    modifier: Modifier = Modifier,
    hasLabel: Boolean = true,
    value: Float = 10f,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..30f,
    labelMinWidth: Dp = 24.dp,
    steps: Int = 10,
    labelSize: Int = 35,
    onValueChangeFinished: (() -> Unit)? = {},
    cornerRadius: Dp = 30.dp,
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    handleColor: Color = MaterialTheme.colors.primary,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    handleSize: DpSize = DpSize(30.dp, 30.dp),
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
    backgroundColor: Color = MaterialTheme.colors.background,
    labelColor: Color = MaterialTheme.colors.onBackground,
) {
    Column(modifier = modifier) {
        if (hasLabel) {
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
                        .padding(
                            start = offset,
                            bottom = 4.dp
                        )
                        .size(labelSize.dp)
                )
            }
        }
        NeuSliderBar(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            onValueChangeFinished = onValueChangeFinished,
            cornerRadius = cornerRadius,
            elevation = elevation,
            lightSource = lightSource,
            handleColor = handleColor,
            backgroundColor = backgroundColor,
            handleSize = handleSize,
            lightShadowColor = lightShadowColor,
            darkShadowColor = darkShadowColor,
            iconColor = iconColor,
            modifier = Modifier.fillMaxWidth()
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
