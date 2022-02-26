package com.adwi.neumorph.sample.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adwi.neumorph.android.components.PreviewTemplate

@Composable
private fun OptionsPanel(
    elevation: Float = 10f,
    onElevationChange: (Float) -> Unit,
    cornerRadius: Float = 10f,
    onCornerRadiusChange: (Float) -> Unit,
) {
    OptionsItem(
        title = "Elevation",
        value = elevation,
        onValueChange = onElevationChange,
        valueRange = 0f..30f,
        steps = 10
    )
    OptionsItem(
        title = "Corner radius",
        value = cornerRadius,
        onValueChange = onCornerRadiusChange,
        valueRange = 0f..50f,
        steps = 10
    )
}


@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsPanelLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        OptionsPanel(
            elevation = 10f,
            onElevationChange = {},
            cornerRadius = 10f,
            onCornerRadiusChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun OptionsPanelDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        OptionsPanel(
            elevation = 10f,
            onElevationChange = {},
            cornerRadius = 10f,
            onCornerRadiusChange = {}
        )
    }
}
