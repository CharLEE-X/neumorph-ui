package com.adwi.neumorph.sample.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun Header(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.h4.copy(MaterialTheme.colors.onBackground.copy(alpha = .8f)),
) {
    Text(
        text = text,
        style = style
    )
}
