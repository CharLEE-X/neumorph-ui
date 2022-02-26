@file:OptIn(ExperimentalMaterialApi::class)

package com.adwi.neumorph.android

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.adwi.neumorph.android.components.MorphIcon
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.theme.AppColors
import com.adwi.neumorph.android.util.Constants.PRESS_SWITCH_DURATION

/**
 * Neumorph version of Material Switch
 *
 * @param modifier The companion object Modifier is the empty, default, or starter Modifier that contains no elements.
 * @param value off switch: true or false
 * @param onValueChange sets value
 * @param cornerRadius corner radius applied to all corners
 * @param shape applies shape to component
 * @param elevation size of shadow˚ applied to component
 * @param lightSource defines direction to cast shadow
 * @param backgroundColor color applied behind switch indicator
 * @param switchColor color of switch indicator
 * @param lightShadowColor color of lighter shadow
 * @param darkShadowColor color of darker shadow
 */
@Composable
fun MorphSwitch(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: () -> Unit,
    cornerRadius: Dp = 30.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    backgroundColor: Color = MaterialTheme.colors.surface,
    switchColor: Color = MaterialTheme.colors.primary,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
) {
    val switchColorState by animateColorAsState(
        targetValue = if (value) switchColor else MaterialTheme.colors.primaryVariant,
        animationSpec = tween(200)
    )
    val contentColorState by animateColorAsState(
        targetValue = if (value) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        animationSpec = tween(200)
    )

    Box(
        modifier = modifier
            .height(100.dp)
            .width(200.dp)
            .wrapContentSize()
    ) {
        val falseConstrainSet = ConstraintSet("""{
            switch: {
              top: ['parent', 'top', 0],
              bottom: ['parent', 'bottom', 0],
              start: ['parent', 'start', 0]
            }
            }""")
        val trueConstrainSet = ConstraintSet("""{
            switch: {
              top: ['parent', 'top', 0],
              bottom: ['parent', 'bottom', 0],
              end: ['parent', 'end', 0]
            }
            }""")
        val constrains = if (value) trueConstrainSet else falseConstrainSet
        MorphPressed(
            onClick = onValueChange,
            cornerRadius = cornerRadius,
            backgroundColor = backgroundColor,
            shape = shape,
            lightShadowColor = lightShadowColor,
            darkShadowColor = darkShadowColor,
            elevation = elevation,
            lightSource = lightSource,
            modifier = Modifier,
            content = {}
        )
        ConstraintLayout(
            constraintSet = constrains,
            animateChanges = true,
            animationSpec = tween(PRESS_SWITCH_DURATION),
            modifier = Modifier.fillMaxSize(),
        ) {
            RoundedIndicator(
                onValueChange = onValueChange,
                cornerRadius = cornerRadius,
                switchColor = switchColorState,
                modifier = Modifier
                    .layoutId("switch")
                    .fillMaxWidth(.5f)
            ) {
                MorphIcon(
                    icon = Icons.Default.Menu,
                    tint = contentColorState,
                    modifier = Modifier
                        .fillMaxSize(.4f)
                        .rotate(90f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundedIndicator(
    modifier: Modifier = Modifier,
    onValueChange: () -> Unit,
    cornerRadius: Dp = 30.dp,
    switchColor: Color = MaterialTheme.colors.primary,
    content: @Composable () -> Unit
) {
    Surface(
        onClick = onValueChange,
        color = switchColor,
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp,
        indication = null,
        modifier = modifier

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun MorphSwitchLightPreview() {
    PreviewTemplate(darkTheme = false, name = "MorphCardPunched") {
        MorphSwitch(value = true, onValueChange = {})
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun MorphSwitchDarkPreview() {
    PreviewTemplate(darkTheme = true, name = "MorphCardPunched") {
        MorphSwitch(value = true, onValueChange = {})
    }
}
