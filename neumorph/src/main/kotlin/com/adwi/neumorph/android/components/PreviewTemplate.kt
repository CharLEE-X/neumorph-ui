package com.adwi.neumorph.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.theme.MorphUiTheme

@Composable
fun PreviewTemplate(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    name: String = "Composable",
    content: @Composable (content: @Composable () -> Unit) -> Unit,
) {
    MorphUiTheme(darkTheme = darkTheme) {
        MorphBackground {
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
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colors.onBackground
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
        PreviewTemplate() {
            it()
        }
    }
}
