package com.adwi.neumorph.sample.ui.components

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.NeuPressed

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SliderLabel(
    modifier: Modifier = Modifier,
    value: Int,
    elevation: Dp = 8.dp,
    cornerRadius: Dp = 4.dp,
    backgroundColor: Color = MaterialTheme.colors.background,
    labelColor: Color = MaterialTheme.colors.onBackground,
) {
    NeuPressed(
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        elevation = elevation,
        modifier = modifier
    ) {
        Text(
            text = "$value",
            color = labelColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}
