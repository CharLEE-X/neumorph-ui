package com.adwi.neumorph.sample.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.MorphPressed
import com.adwi.neumorph.android.components.ColorPicker
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.theme.pickerColors

@Composable
fun ItemPanel(
    title: String,
    onClick: (isExpanded: Boolean) -> Unit = {},
    hasCornerRadius: Boolean = true,
    elevationRange: ClosedFloatingPointRange<Float> = 0f..20f,
    cornerRadiusRange: ClosedFloatingPointRange<Float> = 0f..40f,
    content: @Composable (
        elevationState: Float,
        cornerRadiusState: Float,
        currentColor: Color?,
    ) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    var elevation by remember { mutableStateOf(10f) }
    var cornerRadius by remember { mutableStateOf(20f) }
    val (selectedColor, onColorSelected) = remember { mutableStateOf(pickerColors[0]) }
//    println("elevation = $elevation")
//    println("cornerRadius = $cornerRadius")

    Column {
        ItemHeader(
            text = title,
            onClick = {
                expanded = !expanded
                onClick(expanded)
            },
            isExpanded = expanded,
            contentElevation = elevation,
            contentCorners = cornerRadius,
            contentColor = selectedColor,
            content = { elevation, corners, color, padding ->
                Box(
                    modifier = Modifier
                        .padding(bottom = padding)
                        .padding(horizontal = padding)
                ) {
                    content(elevation, corners, color)
                }
            },
            options = {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    OptionsItem(
                        title = "Elevation",
                        value = elevation,
                        onValueChange = {
                            elevation = it
                            println("newElevation = $elevation")
                        },
                        valueRange = elevationRange,
                    )
                    if (hasCornerRadius) {
                        OptionsItem(
                            title = "Corner radius",
                            value = cornerRadius,
                            onValueChange = { cornerRadius = it },
                            valueRange = cornerRadiusRange,
                        )
                    }
                    ColorPicker(
                        colors = pickerColors,
                        selectedColor = selectedColor,
                        onColorSelected = onColorSelected
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHeader(
    text: String,
    onClick: () -> Unit,
    isExpanded: Boolean = false,
    contentElevation: Float,
    contentCorners: Float,
    contentColor: Color?,
    content: @Composable (
        elevationState: Float,
        cornerRadiusState: Float,
        currentColor: Color?,
        paddingState: Dp,
    ) -> Unit,
    options: @Composable () -> Unit,
) {
    val duration = 200
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(duration)
    )
    val verticalPaddingState by animateDpAsState(
        targetValue = if (isExpanded) 24.dp else 8.dp,
        animationSpec = tween(duration)
    )
    val horizontalPaddingState by animateDpAsState(
        targetValue = if (isExpanded) 18.dp else 8.dp,
        animationSpec = tween(duration)
    )
    val elevationState by animateDpAsState(
        targetValue = if (isExpanded) 10.dp else 0.dp,
        animationSpec = tween(duration)
    )
    val cornerState by animateDpAsState(
        targetValue = if (isExpanded) 30.dp else 0.dp,
        animationSpec = tween(duration)
    )

    MorphPressed(
        backgroundColor = Color.Transparent,
        elevation = elevationState,
        cornerRadius = cornerState,
        modifier = Modifier
            .animateContentSize(tween(300))
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = verticalPaddingState)
                .padding(horizontal = horizontalPaddingState)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick)
            ) {
                Header(text = text)
                Spacer(modifier = Modifier
                    .defaultMinSize(8.dp)
                    .weight(1f))
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Show more",
                    modifier = Modifier
                        .rotate(rotationState)
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            content(contentElevation, contentCorners, contentColor, verticalPaddingState)
            if (isExpanded) {
                options()
            }
        }
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
fun HomeItemPreviewLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        ItemPanel(title = "Coming soon") { elevation, corners, color ->
            MorphPressed(
                elevation = elevation.dp,
                cornerRadius = corners.dp,
                backgroundColor = color ?: MaterialTheme.colors.surface,
                modifier = Modifier
                    .height(100.dp),
                content = { Text(text = "Bottom Nav") }
            )
        }
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun HomeItemPreviewDark() {
    PreviewTemplate(
        darkTheme = true,
    ) { text ->
        ItemPanel(title = "Coming soon") { elevation, corners, color ->
            MorphPressed(
                elevation = elevation.dp,
                cornerRadius = corners.dp,
                backgroundColor = color ?: MaterialTheme.colors.surface,
                modifier = Modifier
                    .height(100.dp),
                content = { Text(text = "Bottom Nav") }
            )
        }
    }
}
