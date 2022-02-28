package com.adwi.neumorph.android.picker

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

internal fun DrawScope.drawHorizontalSelector(
    amount: Float,
    color: Color,
    handleColor: Color = Color.White,
) {
    val halfIndicatorThickness = 4.dp.toPx()
    val strokeThickness = 4.dp.toPx()

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
        handleColor = handleColor
    )
}

internal fun DrawScope.drawSelectorIndicator(
    offset: Offset,
    selectionSize: Size,
    strokeThicknessPx: Float,
    color: Color,
    handleColor: Color,
) {
    val selectionStyle = Stroke(strokeThicknessPx)
    drawRect(
        handleColor,
        topLeft = offset,
        size = selectionSize,

    )
    drawRect(
        color,
        topLeft = offset + Offset(strokeThicknessPx, strokeThicknessPx),
        size = selectionSize.inset(2 * strokeThicknessPx),
        style = selectionStyle
    )
}

internal fun Size.inset(amount: Float): Size {
    return Size(width - amount, height - amount)
}