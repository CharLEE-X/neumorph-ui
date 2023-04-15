package com.adwi.neumorph.sample.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.NeuButtonOval
import com.adwi.neumorph.components.NeuButtonRounded
import com.adwi.neumorph.components.NeuColorPicker
import com.adwi.neumorph.components.NeuPressed
import com.adwi.neumorph.components.NeuPunched
import com.adwi.neumorph.components.NeuRadioButton
import com.adwi.neumorph.components.NeuSliderBar
import com.adwi.neumorph.components.NeuSwitch
import com.adwi.neumorph.components.composables.NeuIcon
import com.adwi.neumorph.components.composables.picker.HsvColor
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.sample.ui.components.PreviewTemplate

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        var radioValue by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            NeuRadioButton(
                value = radioValue,
                onValueChange = { radioValue = !radioValue },
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Radio button",
                color = MaterialTheme.colors.onBackground
            )
        }
        var switchValue by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            NeuSwitch(
                value = switchValue,
                onValueChange = { switchValue = !switchValue },
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Switch",
                color = MaterialTheme.colors.onBackground
            )
        }
        NeuButtonRounded(
            elevation = 10.dp,
            cornerRadius = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .height(90.dp)
                .width(250.dp)
                .padding(bottom = 12.dp),
            content = {
                Text(
                    text = "Neumorph UI",
                    color = MaterialTheme.colors.onSurface
                )
            }
        )
        NeuButtonOval(
            elevation = 10.dp,
            backgroundColor = MaterialTheme.colors.surface,
            lightSource = LightSource.LEFT_TOP,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(70.dp),
            content = { NeuIcon() }
        )
        NeuPressed(
            elevation = 10.dp,
            cornerRadius = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .height(100.dp),
            content = {
                Text(
                    text = "Neumorph UI",
                    color = MaterialTheme.colors.onSurface
                )
            }
        )
        NeuPunched(
            elevation = 10.dp,
            cornerRadius = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            content = {
                Text(
                    text = "Neumorph UI",
                    color = MaterialTheme.colors.onSurface
                )
            }
        )
        var sliderValue by remember { mutableStateOf(0.5f) }
        NeuSliderBar(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            elevation = 10.dp,
            lightSource = LightSource.LEFT_TOP,
            cornerRadius = 10.dp,
            handleColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        val colorValue = remember { mutableStateOf(Color.Red) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            NeuButtonOval(
                elevation = 10.dp,
                backgroundColor = colorValue.value,
                lightSource = LightSource.LEFT_TOP,
                modifier = Modifier.size(70.dp),
                content = { }
            )
            Spacer(modifier = Modifier.size(24.dp))
            NeuColorPicker(
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
        NeuPressed(
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
