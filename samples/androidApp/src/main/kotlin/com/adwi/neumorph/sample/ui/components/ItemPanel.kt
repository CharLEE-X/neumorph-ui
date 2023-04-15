package com.adwi.neumorph.sample.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Light
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.adwi.neumorph.components.NeuPressed
import com.adwi.neumorph.components.composables.ColorPicker
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.sample.ui.theme.pickerColors

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
        lightSource: LightSource,
    ) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    var elevation by remember { mutableStateOf(10f) }
    var cornerRadius by remember { mutableStateOf(20f) }
    val (selectedColor, onColorSelected) = remember { mutableStateOf(pickerColors[0]) }
    var lightSource by remember { mutableStateOf(LightSource.LEFT_TOP) }

    Column {
        ItemHeader(
            text = title,
            onClick = {
                expanded = !expanded
                onClick(expanded)
            },
            isExpanded = expanded,
            content = { padding ->
                Box(
                    modifier = Modifier.padding(horizontal = padding)
                ) {
                    LightSources(
                        isExpanded = expanded,
                        lightSource = lightSource,
                        onLightClicked = { lightSource = it },
                        modifier = Modifier.padding(bottom = padding)
                    ) {
                        content(elevation, cornerRadius, selectedColor, lightSource)
                    }
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

@Composable
fun LightSources(
    modifier: Modifier = Modifier,
    onLightClicked: (LightSource) -> Unit,
    lightSource: LightSource,
    isExpanded: Boolean,
    content: @Composable () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        val (comp, lightLT, lightRT, lightLB, lightRB) = createRefs()
        val margin = 8.dp

        Box(modifier = Modifier.constrainAs(comp) {
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        }) {
            content()
        }
        if (isExpanded) {
            SingleLight(
                onClick = { onLightClicked(LightSource.LEFT_TOP) },
                isActive = lightSource == LightSource.LEFT_TOP,
                rotate = 315f,
                isExpanded = isExpanded,
                modifier = Modifier.constrainAs(lightLT) {
                    bottom.linkTo(comp.top, margin = margin)
                    end.linkTo(comp.start, margin = margin)
                }
            )
            SingleLight(
                onClick = { onLightClicked(LightSource.RIGHT_TOP) },
                isActive = lightSource == LightSource.RIGHT_TOP,
                rotate = 45f,
                isExpanded = isExpanded,
                modifier = Modifier.constrainAs(lightRT) {
                    bottom.linkTo(comp.top, margin = margin)
                    start.linkTo(comp.end, margin = margin)
                }
            )
            SingleLight(
                onClick = { onLightClicked(LightSource.LEFT_BOTTOM) },
                isActive = lightSource == LightSource.LEFT_BOTTOM,
                rotate = 225f,
                isExpanded = isExpanded,
                modifier = Modifier.constrainAs(lightLB) {
                    top.linkTo(comp.bottom, margin = margin)
                    end.linkTo(comp.start, margin = margin)
                }
            )
            SingleLight(
                onClick = { onLightClicked(LightSource.RIGHT_BOTTOM) },
                isActive = lightSource == LightSource.RIGHT_BOTTOM,
                rotate = 135f,
                isExpanded = isExpanded,
                modifier = Modifier.constrainAs(lightRB) {
                    top.linkTo(comp.bottom, margin = margin)
                    start.linkTo(comp.end, margin = margin)
                }
            )
        }
    }
}

@Composable
fun SingleLight(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isActive: Boolean,
    isExpanded: Boolean,
    rotate: Float,
) {
    val lightColor = Color(0xFFD6D600)
    val colorState by animateColorAsState(
        targetValue = if (isActive) lightColor else Color.Gray,
        animationSpec = tween(500)
    )
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) rotate else rotate - 90,
        animationSpec = tween(300)
    )
    Icon(
        imageVector = Icons.Default.Light,
        contentDescription = "",
        tint = colorState,
        modifier = modifier
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            )
            .rotate(rotationState)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHeader(
    text: String,
    onClick: () -> Unit,
    isExpanded: Boolean = false,
    content: @Composable (paddingState: Dp) -> Unit,
    options: @Composable () -> Unit,
) {
    val duration = 200
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(duration)
    )
    val verticalPaddingState by animateDpAsState(
        targetValue = if (isExpanded) 24.dp else 0.dp,
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

    NeuPressed(
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
                Spacer(
                    modifier = Modifier
                        .defaultMinSize(8.dp)
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Show more",
                    modifier = Modifier
                        .rotate(rotationState)
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            content(verticalPaddingState)
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
        ItemPanel(title = "Coming soon") { elevation, corners, color, lightSource ->
            NeuPressed(
                elevation = elevation.dp,
                cornerRadius = corners.dp,
                backgroundColor = color ?: MaterialTheme.colors.surface,
                lightSource = lightSource,
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
    ) {
        ItemPanel(title = "Coming soon") { elevation, corners, color, lightSource ->
            NeuPressed(
                elevation = elevation.dp,
                cornerRadius = corners.dp,
                lightSource = lightSource,
                backgroundColor = color ?: MaterialTheme.colors.surface,
                modifier = Modifier
                    .height(100.dp),
                content = { Text(text = "Bottom Nav") }
            )
        }
    }
}
