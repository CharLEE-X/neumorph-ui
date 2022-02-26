package com.adwi.neumorph.android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.attributes.PressedSpec
import com.adwi.neumorph.android.components.MorphSurface
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.neumorph.neu
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.neumorph.shape.RoundedCorner
import com.adwi.neumorph.android.theme.AppColors

/**
 * Neumorph version of Material Switch
 *
 * @param modifier The companion object Modifier is the empty, default, or starter Modifier that contains no elements.
 * @param onClick action when component is clicked
 * @param cornerRadius corner radius applied to all corners
 * @param shape applies shape to component
 * @param elevation size of shadow˚ applied to component
 * @param lightSource defines direction to cast shadow
 * @param backgroundColor color applied behind switch indicator
 * @param lightShadowColor color of lighter shadow
 * @param darkShadowColor color of darker shadow
 * @param border defines components border style
 * @param hasIndication if true shows onClick indication ripple effect
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MorphPunched(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 20.dp,
    elevation: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor: Color = MaterialTheme.colors.surface,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    border: BorderStroke? = null,
    lightSource: LightSource = LightSource.LEFT_TOP,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    hasIndication: Boolean = false,
    content: @Composable () -> Unit,
) {
    PressedSpec(
        isPressed = false,
        elevation = elevation,
        cornerRadius = cornerRadius
    ) { _, corners, _ ->
        MorphSurface(
            onClick = onClick,
            cornerRadius = corners,
            backgroundColor = backgroundColor,
            contentColor = Color.Transparent,
            shape = shape,
            border = border,
            scale = 1f,
            interactionSource = interactionSource,
            hasIndication = hasIndication,
            modifier = modifier
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = elevation,
                    lightSource = lightSource,
                    shape = Punched(RoundedCorner(cornerRadius))
                ),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphPunchedLightPreview() {
    PreviewTemplate(
        darkTheme = false,
        name = "MorphCardPunched"
    ) { text ->
        MorphPunched(
            cornerRadius = 10.dp,
            elevation = 10.dp,
            backgroundColor = MaterialTheme.colors.primary,
            lightSource = LightSource.LEFT_TOP,
            content = text
        )
    }
}

@Preview(showBackground = true, name = "Dark Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphPunchedDarkPreview() {
    PreviewTemplate(
        darkTheme = true,
        name = "MorphCardPunched"
    ) { text ->
        MorphPunched(content = text)
    }
}
