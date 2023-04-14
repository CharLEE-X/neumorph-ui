package com.adwi.neumorph.neumorph

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Paint

internal actual fun nativePaint(color: Int, blurRadius: Float): Paint {
    return Paint().also { paint ->
        paint.asFrameworkPaint().also { nativePaint ->
            nativePaint.isAntiAlias = true
            nativePaint.isDither = true
            nativePaint.color = color
            nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
        }
    }
}
