package com.adwi.neumorph.android.picker

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.theme.AppColors

internal fun DrawScope.drawHorizontalSelector(
    amount: Float,
    color: Color,
    handleColor: Color = Color.White,
    cornerRadius: Dp = 20.dp,
) {
    val halfIndicatorThickness = 4.dp.toPx()
    val strokeThickness = 5.dp.toPx()

    val offset =
        Offset(
            x = amount - halfIndicatorThickness,
            y = -strokeThickness
        )

    val selectionSize = Size(halfIndicatorThickness * 3f, this.size.height + strokeThickness * 2)
    drawSelectorIndicator(
        offset = offset,
        selectionSize = selectionSize,
        strokeThicknessPx = strokeThickness,
        color = color,
        handleColor = handleColor,
        cornerRadius = cornerRadius
    )
}

internal fun DrawScope.drawSelectorIndicator(
    offset: Offset,
    selectionSize: Size,
    strokeThicknessPx: Float,
    color: Color,
    handleColor: Color = Color.White,
    cornerRadius: Dp = 20.dp,
) {
    val selectionStyle = Stroke(strokeThicknessPx)
    drawRoundRect(
        color = handleColor,
        topLeft = offset,
        size = selectionSize,
        cornerRadius = CornerRadius(cornerRadius.value, cornerRadius.value)
    )
    drawRoundRect(
        color = color,
        topLeft = offset + Offset(strokeThicknessPx, strokeThicknessPx),
        size = selectionSize.inset(2 * strokeThicknessPx),
        style = selectionStyle,
        cornerRadius = CornerRadius(cornerRadius.value, cornerRadius.value)
    )
}

internal fun Size.inset(amount: Float): Size {
    return Size(width - amount, height - amount)
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun HueBarLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        val colorValue by remember { mutableStateOf(HsvColor.from(Color.Green)) }
        HueBar(
            currentColor = colorValue,
            onHueChanged = {},
            cornerRadius = 40.dp,
            elevation = 10.dp,
            modifier = Modifier.height(30.dp)
        )
    }
}