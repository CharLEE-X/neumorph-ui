package com.adwi.neumorph.neumorph.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adwi.neumorph.neumorph.model.MorphStyle
import com.adwi.neumorph.neumorph.drawBackgroundShadows

class Punched(override val cornerShape: CornerShape) : MorphShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, style: MorphStyle) {
        drawScope.drawBackgroundShadows(this, style)
        drawScope.drawContent()
    }
}
