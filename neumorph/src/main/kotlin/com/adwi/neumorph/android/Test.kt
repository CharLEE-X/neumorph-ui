package com.adwi.neumorph.android

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.adwi.neumorph.android.theme.MorphUiTheme
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

//@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 300)
//@Composable
//internal fun TestDark() {
//    PreviewTemplate(darkTheme = false) {
//        val colorValue = remember { mutableStateOf(Color.Green) }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            MorphButtonOval(
//                elevation = 20.dp,
//                backgroundColor = colorValue.value,
//                modifier = Modifier.size(70.dp),
//                content = { }
//            )
//            Spacer(modifier = Modifier.size(24.dp))
//            MorphColorPicker(
//                color = colorValue.value,
//                onColorChanged = { hsvColor: HsvColor ->
//                    colorValue.value = hsvColor.toColor()
//                },
//                elevation = 20.dp,
//                cornerRadius = 20.dp,
//                handleColor = Color.White
//            )
//        }
//    }
//}


val Neutral5 = Color(0x31000000)
val Neutral3 = Color(0x12FFFFFF)
val PrimaryShadowLight = Color(0xFFFCF0ED)
val PrimaryShadowDark = Color(0xFFFABBA6)

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.neumorphicShadow(
    topShadowLight: Color = PrimaryShadowLight,
    topShadowDark: Color = Neutral3,
    bottomShadowLight: Color = PrimaryShadowDark,
    bottomShadowDark: Color = Neutral5,
    alpha: Float = .99f,
    cornerRadius: Dp = 30.dp,
    shadowRadius: Dp = 10.dp,
    offsetX: Float = 10f,
    offsetY: Float = 10f,
) = composed {
    val isLightTheme = !isSystemInDarkTheme()

    this.drawBehind {
        drawShadowBackground(
            isLight = isLightTheme,
            shadowLight = topShadowLight,
            shadowDark = topShadowDark,
            alpha = alpha,
            shadowRadius = shadowRadius,
            cornerRadius = cornerRadius,
            offsetX = offsetX,
            offsetY = offsetY
        )
        drawShadowBackground(
            isLight = isLightTheme,
            shadowLight = bottomShadowLight,
            shadowDark = bottomShadowDark,
            alpha = alpha,
            shadowRadius = shadowRadius,
            cornerRadius = cornerRadius,
            offsetX = -offsetX,
            offsetY = -offsetY
        )
    }
}

fun DrawScope.drawShadowBackground(
    isLight: Boolean,
    shadowLight: Color = PrimaryShadowDark,
    shadowDark: Color = Neutral5,
    alpha: Float = .99f,
    shadowRadius: Dp,
    cornerRadius: Dp,
    offsetX: Float,
    offsetY: Float,
) {
    val colorBottom = if (isLight) shadowLight else shadowDark
    val darkAlpha = if (isLight) alpha else 0.1f

    val transparentColor = colorBottom.copy(alpha = 0.0f).value.toInt()
    val shadowColor = colorBottom.copy(alpha = darkAlpha).value.toLong()

    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor

        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX,
            offsetY,
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornerRadius.toPx(),
            cornerRadius.toPx(),
            paint
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 400)
@Composable
 fun NeumorphicPreviewLight() {
    MorphUiTheme(darkTheme = false) {
        var elevation by remember { mutableStateOf(20f) }
        var isStart by remember { mutableStateOf(true) }
        var isTop by remember { mutableStateOf(true) }

        val offsetXState by animateFloatAsState(
            targetValue = if (isStart) elevation else -elevation,
            animationSpec = tween(800)
        )
        val offsetYState by animateFloatAsState(
            targetValue = if (isTop) elevation else -elevation,
            animationSpec = tween(800)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
        ) {
            Button(onClick = {
                isStart = true
                isTop = true
            }, modifier = Modifier.align(Alignment.TopStart)) { Text("LT") }
            Button(onClick = {
                isStart = false
                isTop = true
            }, modifier = Modifier.align(Alignment.TopEnd)) { Text("RT") }
            Button(onClick = {
                isStart = true
                isTop = false
            }, modifier = Modifier.align(Alignment.BottomStart)) { Text("LB") }
            Button(onClick = {
                isStart = false
                isTop = false
            }, modifier = Modifier.align(Alignment.BottomEnd)) { Text("RB") }
            MorphSliderBar(
                value = elevation,
                onValueChange = {elevation = it},
                valueRange = 0f..30f,
                modifier = Modifier.align(Alignment.BottomCenter).height(30.dp)
            )
            Card(
                shape = RoundedCornerShape(30.dp),
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(100.dp)
                    .align(Alignment.Center)
                    .neumorphicShadow(
                        offsetX = offsetXState,
                        offsetY = offsetYState,
                        cornerRadius = 30.dp
                    ),
                content = { Box(contentAlignment = Alignment.Center) { Text("LB") } }
            )
        }
    }
}
