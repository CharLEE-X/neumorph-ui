package com.adwi.neumorph.sample.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.*
import com.adwi.neumorph.android.MorphSlider
import com.adwi.neumorph.android.components.MorphIcon
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.sample.ui.components.ItemPanel
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
            var radioValue by remember { mutableStateOf(false) }
            ItemPanel(
                title = "Radio Button",
                elevationRange = 1f..30f,
                hasCornerRadius = false,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 0)
                }
            ) { elevation, _, color ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MorphRadioButton(
                        elevation = elevation,
                        radioColor = color ?: MaterialTheme.colors.secondary,
                        value = radioValue,
                        onValueChange = { radioValue = !radioValue },
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(text = "Example radio option")
                }
            }
        }
        item {
            var switchValue by remember { mutableStateOf(false) }
            ItemPanel(
                title = "Switch",
                cornerRadiusRange = 0f..20f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 1)
                }
            ) { elevation, cornerRadius, color ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MorphSwitch(
                        elevation = elevation,
                        switchColor = color ?: MaterialTheme.colors.secondary,
                        value = switchValue,
                        onValueChange = { switchValue = !switchValue },
                        cornerRadius = cornerRadius,
                        modifier = Modifier
                            .height(40.dp)
                            .width(80.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(text = "Example switch option")
                }
            }
        }
        item {
            ItemPanel(
                title = "Button Rounded",
                elevationRange = 1f..70f,
                cornerRadiusRange = 0f..35f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 2)
                }
            ) { elevation, cornerRadius, color ->
                MorphButtonRounded(
                    elevation = elevation,
                    cornerRadius = cornerRadius,
                    backgroundColor = color ?: MaterialTheme.colors.surface,
                    modifier = Modifier
                        .height(70.dp)
                        .width(250.dp),
                    content = { Text(text = "Morph UI", color = MaterialTheme.colors.onSurface) }
                )
            }
        }
        item {
            ItemPanel(
                title = "Button Oval",
                elevationRange = 0f..50f,
                hasCornerRadius = false,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 3)
                }
            ) { elevation, _, color ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MorphButtonOval(
                        elevation = elevation,
                        backgroundColor = color ?: MaterialTheme.colors.surface,
                        modifier = Modifier.size(70.dp),
                        content = { MorphIcon() }
                    )
                    MorphButtonOval(
                        elevation = elevation,
                        backgroundColor = color ?: MaterialTheme.colors.surface,
                        modifier = Modifier.size(70.dp),
                        content = { MorphIcon(icon = Icons.Default.Email) }
                    )
                    MorphButtonOval(
                        elevation = elevation,
                        backgroundColor = color ?: MaterialTheme.colors.surface,
                        modifier = Modifier.size(70.dp),
                        content = { MorphIcon(icon = Icons.Default.ShoppingCart) }
                    )
                }
            }
        }
        item {
            ItemPanel(
                title = "Card Pressed",
                elevationRange = 1f..50f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 4)
                }
            ) { elevation, cornerRadius, color ->
                MorphPressed(
                    elevation = elevation,
                    cornerRadius = cornerRadius,
                    backgroundColor = color ?: MaterialTheme.colors.background,
                    modifier = Modifier
                        .height(100.dp),
                    content = { Text(text = "Morph UI", color = MaterialTheme.colors.onSurface) }
                )
            }
        }
        item {
            ItemPanel(
                title = "Card Punched",
                elevationRange = 1f..50f,
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 5)
                }
            ) { elevation, cornerRadius, color ->
                MorphPunched(
                    elevation = elevation,
                    cornerRadius = cornerRadius,
                    backgroundColor = color ?: MaterialTheme.colors.surface,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    content = { Text(text = "Morph UI", color = MaterialTheme.colors.onSurface) }
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
            ) { elevation, cornerRadius, color ->
                MorphSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    elevation = elevation,
                    cornerRadius = cornerRadius,
                    handleColor = color ?: MaterialTheme.colors.primary
                )
            }
        }
        item {
            ItemPanel(
                title = "Coming soon",
                onClick = { isExpanded ->
                    isExpanded.scrollToItemIfExpanded(scope, lazyListState, 7)
                }
            ) { elevation, cornerRadius, color ->
                MorphPressed(
                    elevation = elevation,
                    cornerRadius = cornerRadius,
                    backgroundColor = color ?: MaterialTheme.colors.background,
                    modifier = Modifier,
                    content = {
                        Text(
                            text =
                            "EditText \nBottom Nav \nApp bar \nPopup \nCircular progress indicator \nDial \nColor Picker Bar",
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
