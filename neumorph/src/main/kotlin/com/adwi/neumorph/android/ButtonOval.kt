package com.adwi.neumorph.android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
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
import com.adwi.neumorph.android.neumorph.shape.Oval
import com.adwi.neumorph.android.neumorph.shape.Pressed
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.theme.AppColors
import com.adwi.neumorph.android.util.Constants.PRESS_BUTTON_DURATION

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MorphButtonOval(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    elevation: Dp = 10.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    border: BorderStroke? = null,
    lightSource: LightSource = LightSource.LEFT_TOP,
    hasIndication: Boolean = false,
    content: @Composable (Color) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    PressedSpec(
        isPressed = isPressed,
        duration = PRESS_BUTTON_DURATION, elevation = elevation, cornerRadius = 0.dp
    ) { elevationState, _, scaleState ->
        MorphSurface(
            onClick = onClick,
            cornerRadius = 0.dp,
            shape = CircleShape,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            border = border,
            scale = scaleState,
            hasIndication = hasIndication,
            interactionSource = interactionSource,
            modifier = modifier
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = elevationState,
                    lightSource = lightSource,
                    shape = Pressed(Oval)
                )
                .neu(
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    shadowElevation = -elevationState,
                    lightSource = lightSource,
                    shape = Punched(Oval)
                ),
            content = { color -> content(color) }
        )
    }
}

@Preview(showBackground = true, name = "Light Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphButtonOvalLightPreview() {
    PreviewTemplate(
        darkTheme = false,
    ) { text ->
        MorphButtonOval(content = { text() })
    }
}

@Preview(showBackground = true, name = "Dark Theme", widthDp = 600, heightDp = 600)
@Composable
private fun MorphButtonOvalDarkPreview() {
    PreviewTemplate(
        darkTheme = true,
    ) { text ->
        MorphButtonOval(content = { text() })
    }
}
