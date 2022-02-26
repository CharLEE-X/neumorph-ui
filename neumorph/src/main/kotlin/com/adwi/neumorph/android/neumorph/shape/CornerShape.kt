package com.adwi.neumorph.android.neumorph.shape

import androidx.compose.ui.unit.Dp

sealed class CornerShape {

}

object None : CornerShape()

object Oval : CornerShape()

class RoundedCorner(val radius: Dp) : CornerShape()
