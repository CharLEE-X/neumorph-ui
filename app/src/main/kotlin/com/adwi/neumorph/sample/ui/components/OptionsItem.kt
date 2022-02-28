package com.adwi.neumorph.sample.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.components.PreviewTemplate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OptionsItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    elevation: Dp = 4.dp,
    cornerRadius: Dp = 4.dp,
    backgroundColor: Color = MaterialTheme.colors.background,
    labelColor: Color = MaterialTheme.colors.onBackground,
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.size(12.dp))
        SliderWithLabel(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            backgroundColor = backgroundColor,
            labelColor = labelColor,
            cornerRadius = cornerRadius,
            elevation = elevation,
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsItemLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        OptionsItem(
            title = "Elevation",
            value = 10f,
            onValueChange = {},
            valueRange = 1f..50f,
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsItemDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        OptionsItem(
            title = "Elevation",
            value = 10f,
            onValueChange = {},
            valueRange = 1f..50f,
        )
    }
}
