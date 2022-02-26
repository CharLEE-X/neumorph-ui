package com.adwi.neumorph.android.neumorph.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adwi.neumorph.android.neumorph.model.MorphStyle
import com.adwi.neumorph.android.neumorph.drawForegroundShadows

class Pressed(override val cornerShape: CornerShape) : MorphShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, style: MorphStyle) {
        drawScope.drawContent()
        drawScope.drawForegroundShadows(this, style)
    }
}
