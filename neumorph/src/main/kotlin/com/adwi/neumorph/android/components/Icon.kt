package com.adwi.neumorph.android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.MorphPunched

@Composable
fun MorphIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.ThumbUp,
    contentDescription: String = "",
    tint: Color = MaterialTheme.colors.primary,
    size: Dp = 25.dp,
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size)
    )
}

@Preview(name = "Light", widthDp = 400, heightDp = 400)
@Composable
private fun MorphIconPreviewLight() {
    PreviewTemplate(
        darkTheme = false
    ) {
        MorphPunched(
            modifier = Modifier
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                MorphIcon()
            }
        }
    }
}

@Preview(name = "Dark", widthDp = 400, heightDp = 400)
@Composable
private fun MorphIconPreviewDark() {
    PreviewTemplate(
        darkTheme = true
    ) {
        MorphPunched(
            modifier = Modifier
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                MorphIcon()
            }
        }
    }
}
