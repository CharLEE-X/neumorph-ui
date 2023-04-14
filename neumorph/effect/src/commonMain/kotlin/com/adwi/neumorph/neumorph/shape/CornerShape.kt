package com.adwi.neumorph.neumorph.shape

import androidx.compose.ui.unit.Dp

sealed class CornerShape {

}

object Oval : CornerShape()

class RoundedCorner(val radius: Dp) : CornerShape()
