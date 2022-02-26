package com.adwi.neumorph.android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.attributes.PressedSpec
import com.adwi.neumorph.android.components.MorphSurface
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.neumorph.neu
import com.adwi.neumorph.android.neumorph.shape.Pressed
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.neumorph.shape.RoundedCorner
import com.adwi.neumorph.android.theme.AppColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MorphButtonRounded(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 40.dp,
    elevation: Dp = 10.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    border: BorderStroke? = null,
    lightSource: LightSource = LightSource.LEFT_TOP,
    invertedBackgroundColors: Boolean = true,
    hasIndication: Boolean = false,
    content: @Composable (Color) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    PressedSpec(
        isPressed = isPressed,
        elevation = elevation, cornerRadius = cornerRadius
    ) { elevationState, cornerRadiusState, scaleState ->
        MorphSurface(
            onClick = onClick,
            cornerRadius = cornerRadiusState,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            border = border,
            scale = scaleState,
            invertedBackgroundColors = invertedBackgroundColors,
            hasIndication = hasIndication,
            interactionSource = interactionSource,
            modifier = modifier
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = elevationState,
                    lightSource = lightSource,
                    shape = Pressed(RoundedCorner(cornerRadiusState))
                )
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = -elevationState,
                    lightSource = lightSource,
                    shape = Punched(RoundedCorner(cornerRadiusState))
                ),
            content = { color -> content(color) }
        )
    }
}

@Preview(showBackground = true, name = "Light Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphButtonRoundedLightPreview() {
    PreviewTemplate(
        darkTheme = false,
    ) { text ->
        MorphButtonRounded(
            elevation = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            content = { text() }
        )
    }
}

@Preview(showBackground = true, name = "Dark Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphButtonRoundedDarkPreview() {
    PreviewTemplate(
        darkTheme = true,
    ) { text ->
        MorphButtonRounded(content = { text() })
    }
}
