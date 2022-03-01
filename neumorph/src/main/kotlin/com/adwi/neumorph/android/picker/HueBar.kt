package com.adwi.neumorph.android.picker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.components.PreviewTemplate
import com.adwi.neumorph.android.neumorph.LightSource
import com.adwi.neumorph.android.neumorph.neu
import com.adwi.neumorph.android.neumorph.shape.Punched
import com.adwi.neumorph.android.neumorph.shape.RoundedCorner
import com.adwi.neumorph.android.theme.AppColors

/**
 * Hue sidebar Component that invokes onHueChanged when the value is mutated.
 *
 * @param modifier modifiers to set to the hue bar.
 * @param currentColor the initial color to set on the hue bar.
 * @param onHueChanged the callback that is invoked when hue value changes. Hue is between 0 - 360.
 */
@Composable
internal fun HueBar(
    modifier: Modifier = Modifier,
    currentColor: HsvColor,
    onHueChanged: (Float) -> Unit,
    barThickness: Dp = 30.dp,
    handleColor: Color = Color.White,
    cornerRadius: Dp = 20.dp,
    elevation: Dp = 10.dp,
    lightShadowColor: Color = AppColors.lightShadow(),
    darkShadowColor: Color = AppColors.darkShadow(),
    lightSource: LightSource = LightSource.LEFT_TOP,
) {
    val rainbowBrush = remember {
        Brush.horizontalGradient(getRainbowColors())
    }
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .height(barThickness)
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        onHueChanged(getHueFromPoint(down.position.x, size.width.toFloat()))
                        drag(down.id) { change ->
                            change.consumePositionChange()
                            onHueChanged(getHueFromPoint(change.position.x, size.width.toFloat()))
                        }
                    }
                }
            }
            .neu(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                shadowElevation = elevation,
                lightSource = lightSource,
                shape = Punched(RoundedCorner(cornerRadius))
            )
    ) {
        drawRoundRect(
            brush = rainbowBrush,
            cornerRadius = CornerRadius(cornerRadius.value, cornerRadius.value)
        )

        val huePoint = getPointFromHue(color = currentColor, width = this.size.width)
        drawHorizontalSelector(huePoint, color = currentColor.toColor(), handleColor)
    }
}

private fun getRainbowColors(): List<Color> {
    return listOf(
        Color(0xFFFF0040),
        Color(0xFFFF00FF),
        Color(0xFF8000FF),
        Color(0xFF0000FF),
        Color(0xFF0080FF),
        Color(0xFF00FFFF),
        Color(0xFF00FF80),
        Color(0xFF00FF00),
        Color(0xFF80FF00),
        Color(0xFFFFFF00),
        Color(0xFFFF8000),
        Color(0xFFFF0000),
    )
}

private fun getPointFromHue(color: HsvColor, width: Float): Float {
    return width - (color.hue * width / 360f)
}

private fun getHueFromPoint(x: Float, width: Float): Float {
    val newY = x.coerceIn(0f, width)
    return 360f - newY * 360f / width
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun HueBarLight() {
    PreviewTemplate(
        darkTheme = false,
    ) {
        val colorValue by remember { mutableStateOf(HsvColor.from(Color.Green)) }
        HueBar(
            currentColor = colorValue,
            onHueChanged = { colorValue.value = it },
            cornerRadius = 40.dp,
            elevation = 10.dp,
            modifier = Modifier.height(30.dp)
        )
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun HueBarDark() {
    PreviewTemplate(
        darkTheme = true,
    ) {
        val colorValue by remember { mutableStateOf(HsvColor.from(Color.Green)) }
        HueBar(
            currentColor = colorValue,
            onHueChanged = {},
            cornerRadius = 20.dp,
            elevation = 10.dp,
            modifier = Modifier.height(30.dp)
        )
    }
}