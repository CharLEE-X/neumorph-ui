package com.adwi.neumorph.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.picker.HsvColor

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 300)
@Composable
private fun TestDark() {
    PreviewTemplate(darkTheme = false) {
        val colorValue = remember { mutableStateOf(Color.Green) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            MorphButtonOval(
                elevation = 20.dp,
                backgroundColor = colorValue.value,
                modifier = Modifier.size(70.dp),
                content = { }
            )
            Spacer(modifier = Modifier.size(24.dp))
            MorphColorPicker(
                color = colorValue.value,
                onColorChanged = { hsvColor: HsvColor ->
                    colorValue.value = hsvColor.toColor()
                },
                elevation = 20.dp,
                cornerRadius = 20.dp,
                handleColor = Color.White
            )
        }
    }
}
