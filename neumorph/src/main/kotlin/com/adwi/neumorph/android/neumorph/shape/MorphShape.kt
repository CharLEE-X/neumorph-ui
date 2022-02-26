package com.adwi.neumorph.android.neumorph.shape

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.adwi.neumorph.android.neumorph.model.MorphStyle

abstract class MorphShape(open val cornerShape: CornerShape) {
    abstract fun draw(drawScope: ContentDrawScope, style: MorphStyle)
}
