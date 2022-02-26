package com.adwi.neumorph.android.neumorph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp
import com.adwi.neumorph.android.neumorph.model.defaultPressedAttrs
import com.adwi.neumorph.android.neumorph.model.MorphStyle
import com.adwi.neumorph.android.neumorph.model.NeuAttrs
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.neumorph.shape.MorphShape
import com.adwi.neumorph.android.theme.AppColors

/**
 * Extension method to create neumorphic ui on compose-ui components.
 *
 * @param neuAttrs object contains all the neumorphic style attributes..
 * @author Sridhar Subramani
 */
@Composable
fun Modifier.neu(neuAttrs: NeuAttrs) = this.then(
    Modifier.neu(
        neuAttrs.lightShadowColor,
        neuAttrs.darkShadowColor,
        neuAttrs.shadowElevation,
        neuAttrs.shape,
        neuAttrs.lightSource,
    )
)

/**
 * Extension method to create neumorphic ui on compose-ui components.
 *
 * @param lightShadowColor Light shadow color.
 * @param darkShadowColor Dark shadow color.
 * @param shadowElevation Elevation or Depth.
 * @param shape Component shape.
 * @param lightSource Light-source direction used to draw light and dark shadow at different position.
 * @author Sridhar Subramani
 */
@Composable
fun Modifier.neu(
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    shadowElevation: Dp = defaultPressedAttrs().shadowElevation,
    shape: MorphShape = Punched(defaultPressedAttrs().shape.cornerShape),
    lightSource: LightSource = LightSource.LEFT_TOP,
) = this.then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            val style = MorphStyle(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                shadowElevation = shadowElevation,
                lightSource = lightSource
            )
            shape.draw(this, style)
        }
    }
)
