package com.adwi.neumorph.sample.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.MorphButtonOval
import com.adwi.neumorph.android.components.PreviewTemplate

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(32.dp)
    ) {
        MorphButtonOval(
            modifier = Modifier
        ) {
            IconButton(
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Navigate back",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        Header(text = title)
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun TopBarLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        TopBar(title = "Playground", onClick = {})

    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun TopBarDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        TopBar(title = "Playground", onClick = {})
    }
}
