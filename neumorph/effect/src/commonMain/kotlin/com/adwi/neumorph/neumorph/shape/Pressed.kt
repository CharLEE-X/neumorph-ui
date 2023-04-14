package com.adwi.neumorph.neumorph.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adwi.neumorph.neumorph.model.MorphStyle
import com.adwi.neumorph.neumorph.drawForegroundShadows

class Pressed(override val cornerShape: CornerShape) : MorphShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, style: MorphStyle) {
        drawScope.drawContent()
        drawScope.drawForegroundShadows(this, style)
    }
}
