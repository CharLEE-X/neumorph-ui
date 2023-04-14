package com.adwi.neumorph.sample.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import com.adwi.neumorph.sample.ui.components.ItemPanel
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
            ItemPanel(
                title = "Radio Button",
                elevationRange = 1f..10f,
                hasCornerRadius = false,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 0)
                }
            ) { elevation, _, color, lightSource ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MorphRadioButton(
                        elevation = elevation.dp,
                        radioColor = color ?: MaterialTheme.colors.secondary,
                        lightSource = lightSource,
                        value = radioValue,
                        onValueChange = { radioValue = !radioValue },
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(text = "Radio button")
                }
            }
        }
        item {
            var switchValue by remember { mutableStateOf(true) }
            ItemPanel(
                title = "Switch",
                cornerRadiusRange = 0f..20f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 1)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MorphSwitch(
                        elevation = elevation.dp,
                        switchColor = color ?: MaterialTheme.colors.secondary,
                        lightSource = lightSource,
                        value = switchValue,
                        onValueChange = { switchValue = !switchValue },
                        cornerRadius = cornerRadius.dp,
                        modifier = Modifier
                            .height(40.dp)
                            .width(80.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(text = "Switch")
                }
            }
        }
        item {
            var expanded by remember { mutableStateOf(false) }
            ItemPanel(
                title = "Button Rounded",
                elevationRange = 1f..30f,
                cornerRadiusRange = 0f..35f,
                onClick = { isExpanded ->
                    expanded = isExpanded
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 2)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                val buttonWidthState by animateDpAsState(
                    targetValue = if (expanded) 200.dp else 250.dp,
                    animationSpec = tween(300)
                )
                MorphButtonRounded(
                    elevation = elevation.dp,
                    cornerRadius = cornerRadius.dp,
                    lightSource = lightSource,
                    backgroundColor = color ?: MaterialTheme.colors.surface,
                    modifier = Modifier
                        .height(90.dp)
                        .width(buttonWidthState)
                        .padding(bottom = 12.dp),
                    content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
                )
            }
        }
        item {
            ItemPanel(
                title = "Button Oval",
                elevationRange = 0f..30f,
                hasCornerRadius = false,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 3)
                }
            ) { elevation, _, color, lightSource ->
                MorphButtonOval(
                    elevation = elevation.dp,
                    backgroundColor = color ?: MaterialTheme.colors.surface,
                    lightSource = lightSource,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .size(70.dp),
                    content = { MorphIcon() }
                )
            }
        }
        item {
            ItemPanel(
                title = "Card Pressed",
                elevationRange = 1f..30f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 4)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                MorphPressed(
                    elevation = elevation.dp,
                    cornerRadius = cornerRadius.dp,
                    lightSource = lightSource,
                    backgroundColor = color ?: MaterialTheme.colors.background,
                    modifier = Modifier
                        .height(100.dp),
                    content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
                )
            }
        }
        item {
            ItemPanel(
                title = "Card Punched",
                elevationRange = 1f..30f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 5)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                MorphPunched(
                    elevation = elevation.dp,
                    cornerRadius = cornerRadius.dp,
                    lightSource = lightSource,
                    backgroundColor = color ?: MaterialTheme.colors.surface,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    content = { Text(text = "Neumorph UI", color = MaterialTheme.colors.onSurface) }
                )
            }
        }
        item {
            var sliderValue by remember { mutableStateOf(0.5f) }
            ItemPanel(
                title = "Slider",
                elevationRange = 1f..20f,
                cornerRadiusRange = 0f..20f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 6)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                MorphSliderBar(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    elevation = elevation.dp,
                    lightSource = lightSource,
                    cornerRadius = cornerRadius.dp,
                    handleColor = color ?: MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
        item {
            val colorValue = remember { mutableStateOf(Color.Red) }

            ItemPanel(
                title = "Color picker",
                elevationRange = 1f..30f,
                cornerRadiusRange = 0f..40f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 7)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MorphButtonOval(
                        elevation = elevation.dp,
                        backgroundColor = colorValue.value,
                        lightSource = lightSource,
                        modifier = Modifier.size(70.dp),
                        content = { }
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    MorphColorPicker(
                        color = colorValue.value,
                        onColorChanged = { hsvColor: HsvColor ->
                            colorValue.value = hsvColor.toColor()
                        },
                        elevation = elevation.dp,
                        cornerRadius = cornerRadius.dp,
                        lightSource = lightSource,
                        handleColor = color
                            ?: if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        item {
            ItemPanel(
                title = "Coming soon",
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 8)
                }
            ) { elevation, cornerRadius, color, lightSource ->
                MorphPressed(
                    elevation = elevation.dp,
                    cornerRadius = cornerRadius.dp,
                    lightSource = lightSource,
                    backgroundColor = color ?: MaterialTheme.colors.background,
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
}

fun Boolean.scrollToItemIfExpanded(
    scope: CoroutineScope,
    listState: LazyListState,
    itemIndex: Int,
    offset: Int = 0,
) {
    if (this) {
        scope.launch {
            listState.animateScrollToItem(itemIndex, offset)
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
