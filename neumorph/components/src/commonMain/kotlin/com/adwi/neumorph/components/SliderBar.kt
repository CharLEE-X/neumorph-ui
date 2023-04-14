package com.adwi.neumorph.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.components.composables.MorphIcon
import com.adwi.neumorph.components.composables.MorphSliderThumb
import com.adwi.neumorph.components.util.slider.SliderValueHorizontal
import com.adwi.neumorph.neumorph.LightSource
import com.adwi.neumorph.neumorph.neu
import com.adwi.neumorph.neumorph.shape.Pressed
import com.adwi.neumorph.neumorph.shape.RoundedCorner

/**
 * Neumorph version of Material Slider
 *
 * @param modifier The companion object Modifier is the empty, default, or starter Modifier that
 * contains no elements.
 * @param value off switch: true or false
 * @param onValueChange sets value
 * @param onValueChangeFinished action after user stops interacting with the slider
 * @param valueRange sets min and max value of the slider
 * @param steps divide slider into number of steps. Only step value can be selected.
 * @param cornerRadius corner radius applied to all corners
 * @param elevation size of shadow˚ applied to component
 * @param lightSource defines direction to cast shadow
 * @param backgroundColor color applied behind switch indicator
 * @param handleColor color of slider's handle
 * @param handleSize size of slider's handle
 * @param lightShadowColor color of lighter shadow
 * @param darkShadowColor color of darker shadow
 */
@Composable
fun MorphSliderBar(
    modifier: Modifier = Modifier,
    value: Float = 20f,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..30f,
    steps: Int = 20,
    onValueChangeFinished: (() -> Unit)? = null,
    cornerRadius: Dp = 30.dp,
    elevation: Dp = 10.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    backgroundColor: Color = MaterialTheme.colors.surface,
    handleColor: Color = MaterialTheme.colors.primary,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    handleSize: DpSize = DpSize(30.dp, 30.dp),
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scaleState by animateFloatAsState(
        targetValue = if (isPressed) .98f else 1f,
        animationSpec = TweenSpec(200)
    )
    val elevationState by animateDpAsState(
        targetValue = if (isPressed) elevation / 2 else elevation,
        animationSpec = TweenSpec(200)
    )

    BoxWithConstraints(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier.fillMaxWidth()
    ) {
        val indicatorWidth = 1f / (valueRange.endInclusive - valueRange.start) * value
        val offset = (this.maxWidth - handleSize.height) * indicatorWidth

        SliderValueHorizontal(
            value = value,
            onValueChange = onValueChange,
            onValueChangeFinished = onValueChangeFinished,
            valueRange = valueRange,
            steps = steps,
            interactionSource = MutableInteractionSource(),
            thumbSizeInDp = handleSize,
            thumbHeightMax = false,
            modifier = Modifier.fillMaxWidth(),
            track = { _, _: Float, _, _, _ ->
//                onValueChange(progress)
                MorphTrack(
                    indicatorWidth = indicatorWidth,
                    cornerRadius = cornerRadius,
                    elevation = elevation,
                    lightSource = lightSource,
                    lightShadowColor = lightShadowColor,
                    darkShadowColor = darkShadowColor,
                    backgroundColor = backgroundColor,
                    contentColor = handleColor,
                    modifier = modifier
                )
            },
            thumb = { _, _, _, _, _ ->
                MorphSliderThumb(
                    offset = offset,
                    size = handleSize.height,
                    cornerRadius = cornerRadius,
                    elevation = elevationState,
                    lightSource = lightSource,
                    lightShadowColor = lightShadowColor,
                    interactionSource = interactionSource,
                    darkShadowColor = darkShadowColor,
                    handleColor = handleColor,
                    modifier = Modifier
                        .graphicsLayer {
                            scaleY = scaleState
                            scaleX = scaleState
                        }
                ) {
                    MorphIcon(
                        icon = Icons.Default.Menu,
                        tint = iconColor,
                        modifier = Modifier
                            .fillMaxSize(.4f)
                            .rotate(90f)
                    )
                }
            },
        )
    }
}

@Composable
private fun MorphTrack(
    modifier: Modifier = Modifier,
    indicatorWidth: Float,
    height: Dp = 10.dp,
    cornerRadius: Dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    elevation: Dp,
    lightSource: LightSource,
    backgroundColor: Color,
    contentColor: Color,
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
) {
    Surface(
        shape = shape,
        color = backgroundColor,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .neu(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                shadowElevation = elevation,
                shape = Pressed(RoundedCorner(cornerRadius)),
                lightSource = lightSource
            )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(indicatorWidth)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(backgroundColor, contentColor)
                        )
                    )
            )
        }
    }
}
