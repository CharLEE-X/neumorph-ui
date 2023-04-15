package com.adwi.neumorph.sample.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.MorphButtonOval
import com.adwi.neumorph.components.MorphButtonRounded
import com.adwi.neumorph.components.MorphColorPicker
import com.adwi.neumorph.components.MorphPressed
import com.adwi.neumorph.components.MorphPunched
import com.adwi.neumorph.components.MorphRadioButton
import com.adwi.neumorph.components.MorphSliderBar
import com.adwi.neumorph.components.MorphSwitch
import com.adwi.neumorph.components.composables.MorphIcon
import com.adwi.neumorph.components.composables.picker.HsvColor
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.sample.ui.components.PreviewTemplate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            var radioValue by remember { mutableStateOf(true) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                MorphRadioButton(
                    elevation = 10.dp,
                    radioColor = MaterialTheme.colors.secondary,
                    lightSource = LightSource.LEFT_TOP,
                    value = radioValue,
                    onValueChange = { radioValue = !radioValue },
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = "Radio button")
            }
        }
        item {
            var switchValue by remember { mutableStateOf(true) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                MorphSwitch(
                    elevation = 10.dp,
                    switchColor = MaterialTheme.colors.secondary,
                    lightSource = LightSource.LEFT_TOP,
                    value = switchValue,
                    onValueChange = { switchValue = !switchValue },
                    cornerRadius = 20.dp,
                    modifier = Modifier
                        .height(40.dp)
                        .width(80.dp)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = "Switch")
            }
        }
        item {
            MorphButtonRounded(
                elevation = 10.dp,
                cornerRadius = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .height(90.dp)
                    .width(250.dp)
                    .padding(bottom = 12.dp),
                content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
            )
        }
        item {
            MorphButtonOval(
                elevation = 10.dp,
                backgroundColor = MaterialTheme.colors.surface,
                lightSource = LightSource.LEFT_TOP,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(70.dp),
                content = { MorphIcon() }
            )
        }
        item {
            MorphPressed(
                elevation = 10.dp,
                cornerRadius = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .height(100.dp),
                content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
            )
        }
        item {
            MorphPunched(
                elevation = 10.dp,
                cornerRadius = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
            )
        }
        item {
            var sliderValue by remember { mutableStateOf(0.5f) }
            MorphSliderBar(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                elevation = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                cornerRadius = 10.dp,
                handleColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        item {
            val colorValue = remember { mutableStateOf(Color.Red) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                MorphButtonOval(
                    elevation = 10.dp,
                    backgroundColor = colorValue.value,
                    lightSource = LightSource.LEFT_TOP,
                    modifier = Modifier.size(70.dp),
                    content = { }
                )
                Spacer(modifier = Modifier.size(24.dp))
                MorphColorPicker(
                    color = colorValue.value,
                    onColorChanged = { hsvColor: HsvColor ->
                        colorValue.value = hsvColor.toColor()
                    },
                    elevation = 10.dp,
                    cornerRadius = 10.dp,
                    lightSource = LightSource.LEFT_TOP,
                    handleColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
        item {
            MorphPressed(
                elevation = 10.dp,
                cornerRadius = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier,
                content = {
                    Text(
                        text =
                        "TextField \nBottom Nav \nApp bar \nPopup \nCircular progress indicator \nDial",
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun HomePreviewLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun HomePreviewDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        HomeScreen()
    }
}
