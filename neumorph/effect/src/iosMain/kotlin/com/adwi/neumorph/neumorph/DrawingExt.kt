package com.adwi.neumorph.neumorph

import androidx.compose.ui.graphics.Paint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

internal actual fun nativePaint(color: Int, blurRadius: Float): Paint {
    return Paint().also { paint ->
        paint.asFrameworkPaint().also { nativePaint ->
            nativePaint.isAntiAlias = true
            nativePaint.isDither = true
            nativePaint.color = color
            nativePaint.maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadius)
        }
    }
}
