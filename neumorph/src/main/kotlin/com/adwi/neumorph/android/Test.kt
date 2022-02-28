package com.adwi.neumorph.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.theme.MorphUiTheme
import com.adwi.neumorph.android.theme.AppColors
import com.adwi.neumorph.android.components.MorphSlider
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.components.SampleText

private val lightBrush =
    Brush.verticalGradient(listOf(AppColors.Light.BackgroundTop, AppColors.Light.BackgroundBottom).reversed())
private val darkBrush =
    listOf(AppColors.Dark.BackgroundTop, AppColors.Dark.BackgroundBottom)

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 300)
@Composable
private fun TestDark() {
    PreviewTemplate(darkTheme = true) {
        MorphPunched(
            elevation = 30.dp,
            cornerRadius = 20.dp,
            modifier = Modifier.fillMaxWidth(.7f).height(150.dp),
        ) {
            SampleText(text = "Neumorph UI")
        }
    }
}
