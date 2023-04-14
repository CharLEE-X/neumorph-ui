package com.adwi.neumorph.components.composables.picker

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
