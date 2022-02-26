package com.adwi.neumorph.android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.adwi.neumorph.android.neumorph.shape.Pressed
import com.adwi.neumorph.android.neumorph.shape.RoundedCorner
import com.adwi.neumorph.android.theme.AppColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MorphPressed(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 60.dp,
    elevation: Dp = (10).dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor: Color = MaterialTheme.colors.background,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    border: BorderStroke? = null,
    scale: Float = 1f,
    lightSource: LightSource = LightSource.LEFT_TOP,
    invertedBackgroundColors: Boolean = false,
    hasIndication: Boolean = false,
    content: @Composable () -> Unit,
) {
    PressedSpec(
        isPressed = true,
        elevation = elevation,
        cornerRadius = cornerRadius
    ) { elevationState, cornerRadiusState, _ ->
        MorphSurface(
            onClick = onClick,
            cornerRadius = cornerRadiusState,
            backgroundColor = backgroundColor,
            contentColor = Color.Transparent,
            shape = shape,
            border = border,
            scale = scale,
            invertedBackgroundColors = invertedBackgroundColors,
            hasIndication = hasIndication,
            interactionSource = MutableInteractionSource(),
            modifier = modifier
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = elevationState,
                    lightSource = lightSource,
                    shape = Pressed(RoundedCorner(cornerRadiusState))
                ),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphPressedLightPreview() {
    PreviewTemplate(
        darkTheme = false,
        name = "MorphCardPunched"
    ) { text ->
        MorphPressed(
            elevation = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            content = text
        )
    }
}

@Preview(showBackground = true, name = "Dark Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphPressedDarkPreview() {
    PreviewTemplate(
        darkTheme = true,
        name = "MorphCardPunched"
    ) { text ->
        MorphPressed(content = text)
    }
}
