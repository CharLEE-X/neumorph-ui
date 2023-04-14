package com.adwi.neumorph.sample.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.sample.ui.theme.NeuColors
import com.adwi.neumorph.sample.ui.theme.NeuTheme

@Composable
fun PreviewTemplate(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    name: String = "Composable",
    content: @Composable (content: @Composable () -> Unit) -> Unit,
) {
    NeuTheme(darkTheme = darkTheme) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        if (darkTheme)
                            listOf(
                                NeuColors.Dark.BackgroundTop,
                                NeuColors.Dark.BackgroundBottom
                            )
                        else
                            listOf(
                                NeuColors.Light.BackgroundTop,
                                NeuColors.Light.BackgroundBottom,
                            )
                    )
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                content { SampleText(text = name) }
            }
        }
    }
}

@Composable
fun SampleText(
    text: String = "Morph UI",
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun PreviewTemplateLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        PreviewTemplate() {
            it()
        }
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun PreviewTemplateDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        PreviewTemplate(darkTheme = true) {
            it()
        }
    }
}
