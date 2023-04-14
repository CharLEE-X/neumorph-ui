package com.adwi.neumorph.components.util.slider

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import com.adwi.neumorph.components.util.lerp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs

/*
* Copyright to com.github.krottv.compose.sliders
* https://github.com/krottv/compose-sliders
*/

/**
 * Horizontal slider that allows to specify custom track and thumb
 *
 * Possible alternative implementations might be: SliderValueVertical, SliderRangeHorizontal, SliderRangeVertical
 *
 * Sliders reflect a range of values along a bar, from which users may select a single value. They are ideal for adjusting settings such as volume, brightness, or applying image filters.
 * Use continuous sliders to allow users to make meaningful selections that don’t require a specific value:
 * Params:
 * value - current value of the Slider. If outside of valueRange provided, value will be coerced to this range.
 * onValueChange - lambda in which value should be updated
 * modifier - modifiers for the Slider layout
 * enabled - whether or not component is enabled and can be interacted with or not
 * valueRange - range of values that Slider value can take. Passed value will be coerced to this range
 * steps - if greater than 0, specifies the amounts of discrete values, evenly distributed between across the whole value range. If 0, slider will behave as a continuous slider and allow to choose any value from the range specified. Must not be negative.
 * onValueChangeFinished - lambda to be invoked when value change has ended. This callback shouldn't be used to update the slider value (use onValueChange for that), but rather to know when the user has completed selecting a new value by ending a drag or a click.
 * interactionSource - the MutableInteractionSource representing the stream of Interactions for this Slider. You can create and pass in your own remembered MutableInteractionSource if you want to observe Interactions and customize the appearance / behavior of this Slider in different Interactions.
 * colors - SliderColors that will be used to determine the color of the Slider parts in different state. See SliderDefaults.colors to customize.

 */
@Composable
fun SliderValueHorizontal(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    /*@IntRange(from = 0)*/
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    thumbSizeInDp: DpSize = DEFAULT_THUMB_SIZE,

    // TODO: automatically detect if passed modifier has unbounded height - then true
    // otherwise false. The goal is for the thumb to have max available height for touch
    // but at the same time don't make parent bigger
    thumbHeightMax: Boolean = false,

    // TODO: use reference for default value when update kotlin
    track: @Composable (
        modifier: Modifier,
        fraction: Float,
        interactionSource: MutableInteractionSource,
        tickFractions: List<Float>,
        enabled: Boolean
    ) -> Unit = { p1, p2, p3, p4, p5 -> DefaultTrack(p1, p2, p3, p4, p5) },

    thumb: @Composable (
        modifier: Modifier,
        offset: Dp,
        interactionSource: MutableInteractionSource,
        enabled: Boolean,
        thumbSize: DpSize
    ) -> Unit = { p1, p2, p3, p4, p5 -> DefaultThumb(p1, p2, p3, p4, p5) }
) {
    require(steps >= 0) { "steps should be >= 0" }

    /**
     * optimisation for a long live lambda
     */
    val onValueChangeState = rememberUpdatedState(onValueChange)

    /**
     * list of fractions that represent ticks. Like [0f, 0.5f, 1f]
     */
    val tickFractions = remember(steps) {
        stepsToTickFractions(steps)
    }

    /**
     * BoxWithConstraints is a layout similar to the Box layout,
     * but it has the advantage that you can get the
     * minimum/maximum available width and height for the Composable on the screen.
     */
    BoxWithConstraints(
        /**
         * minimum size
         */
        modifier = modifier.requiredSizeIn(
            minWidth = thumbSizeInDp.width * 2,
            minHeight = thumbSizeInDp.height
        )
            .sliderSemantics(value, tickFractions, enabled, onValueChange, valueRange, steps)
            //interactionSource - MutableInteractionSource that will be used to emit FocusInteraction.
            // Focus when this element is being focused.
            .focusable(enabled, interactionSource),

        contentAlignment = Alignment.CenterStart

    ) {

        val maxPx = constraints.maxWidth.toFloat()
        val minPx = 0f
        val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

        // value that is scaled to 0..1f range and clipped
        val fraction = calcFraction(
            valueRange.start,
            valueRange.endInclusive,
            value.coerceIn(valueRange.start, valueRange.endInclusive)
        )

        // converts user value in pixels to value in range
        fun scaleToUserValue(offset: Float) =
            scale(
                minPx,
                maxPx,
                offset,
                valueRange.start,
                valueRange.endInclusive
            )

        // converts value in range to offset in pixels
        fun scaleToOffset(userValue: Float) =
            scale(
                valueRange.start,
                valueRange.endInclusive,
                userValue,
                minPx,
                maxPx
            )

        val scope = rememberCoroutineScope()
        val rawOffset = remember { mutableStateOf(scaleToOffset(value)) }

        val draggableState = remember(minPx, maxPx, valueRange) {
            SliderDraggableState {
                rawOffset.value = (rawOffset.value + it).coerceIn(minPx, maxPx)
                onValueChangeState.value.invoke(scaleToUserValue(rawOffset.value))
            }
        }

        CorrectValueSideEffect(::scaleToOffset, valueRange, rawOffset, value)

        // TODO: it might be a good idea to check if value is placed on a tick or not
        // because value is controlled by a user and it can be somewhere on a wrong place
        val gestureEndAction = rememberUpdatedState<(Float) -> Unit> { velocity: Float ->
            val current = rawOffset.value

            // tick value in px
            val target = snapValueToTick(current, tickFractions, minPx, maxPx)
            if (current != target) {
                scope.launch {
                    animateToTarget(draggableState, current, target, velocity)
                    onValueChangeFinished?.invoke()
                }
            } else if (!draggableState.isDragging) {
                // check ifDragging in case the change is still in progress (touch -> drag case)
                onValueChangeFinished?.invoke()
            }
        }

        val offset = (maxWidth - thumbSizeInDp.width) * fraction

        track(
            Modifier
                .padding(horizontal = thumbSizeInDp.width / 2)
                .fillMaxWidth(),
            fraction,
            interactionSource,
            tickFractions,
            enabled
        )

        val press = Modifier.sliderPressModifier(
            draggableState, interactionSource, maxPx, isRtl, rawOffset, gestureEndAction, enabled
        )

        val drag = Modifier.draggable(
            orientation = Orientation.Horizontal,
            reverseDirection = isRtl,
            enabled = enabled,
            interactionSource = interactionSource,
            onDragStopped = { velocity -> gestureEndAction.value.invoke(velocity) },
            startDragImmediately = draggableState.isDragging,
            state = draggableState
        )

        Box(
            Modifier
                .fillMaxWidth()
                .height(if (thumbHeightMax) maxHeight else thumbSizeInDp.height)
                .then(press).then(drag),
            contentAlignment = Alignment.CenterStart
        ) {
            thumb(
                Modifier.padding(start = offset)
                    .size(thumbSizeInDp)
                    .hoverable(interactionSource = interactionSource),
                offset,
                interactionSource,
                enabled,
                thumbSizeInDp
            )
        }
    }
}

// TODO: why semantics(merge=false). It seems like we don't need information from descendants
private fun Modifier.sliderSemantics(
    value: Float,
    tickFractions: List<Float>,
    enabled: Boolean,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0
): Modifier {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    return semantics {
        if (!enabled) disabled()
        setProgress(
            action = { targetValue ->
                val newValue = targetValue.coerceIn(valueRange.start, valueRange.endInclusive)

                // tick value
                val resolvedValue = if (steps > 0) {
                    tickFractions
                        .map { lerp(valueRange.start, valueRange.endInclusive, it) }
                        .minByOrNull { abs(it - newValue) } ?: newValue
                } else {
                    newValue
                }
                // This is to keep it consistent with AbsSeekbar.java: return false if no
                // change from current.
                if (resolvedValue == coerced) {
                    false
                } else {
                    onValueChange(resolvedValue)
                    true
                }
            }
        )
    }.progressSemantics(value, valueRange, steps)
}

private fun snapValueToTick(
    current: Float,
    tickFractions: List<Float>,
    minPx: Float,
    maxPx: Float
): Float {
    // target is a closest anchor to the `current`, if exists
    return tickFractions
        .minByOrNull { abs(lerp(minPx, maxPx, it) - current) }
        ?.run { lerp(minPx, maxPx, this) }
        ?: current
}

private suspend fun animateToTarget(
    draggableState: DraggableState,
    current: Float,
    target: Float,
    velocity: Float
) {
    draggableState.drag {
        var latestValue = current
        Animatable(initialValue = current)
            .animateTo(target, SliderToTickAnimation, velocity) {
                dragBy(this.value - latestValue)
                latestValue = this.value
            }
    }
}

private val SliderToTickAnimation by lazy {
    TweenSpec<Float>(durationMillis = 100)
}

private class SliderDraggableState(
    val onDelta: (Float) -> Unit
) : DraggableState {

    var isDragging by mutableStateOf(false)
        private set

    private val dragScope: DragScope = object : DragScope {
        override fun dragBy(pixels: Float): Unit = onDelta(pixels)
    }

    private val scrollMutex = MutatorMutex()

    override suspend fun drag(
        dragPriority: MutatePriority,
        block: suspend DragScope.() -> Unit
    ): Unit = coroutineScope {
        isDragging = true
        scrollMutex.mutateWith(dragScope, dragPriority, block)
        isDragging = false
    }

    override fun dispatchRawDelta(delta: Float) {
        return onDelta(delta)
    }
}

@Composable
private fun CorrectValueSideEffect(
    scaleToOffset: (Float) -> Float,
    valueRange: ClosedFloatingPointRange<Float>,
    valueState: MutableState<Float>,
    value: Float
) {
    SideEffect {
        // 0.001f
        val error = (valueRange.endInclusive - valueRange.start) / 1000
        // value in px
        val newOffset = scaleToOffset(value)

        // valueState.value = remember { scaleToOffset(value) }.
        // how can it be that it is different?
        // measurement process?
        // TODO: investigate more.

        if (abs(newOffset - valueState.value) > error)
            valueState.value = newOffset
    }
}

private fun Modifier.sliderPressModifier(
    draggableState: DraggableState,
    interactionSource: MutableInteractionSource,
    maxPx: Float,
    isRtl: Boolean,
    rawOffset: State<Float>,
    gestureEndAction: State<(Float) -> Unit>,
    enabled: Boolean
): Modifier =
    if (enabled) {
        pointerInput(draggableState, interactionSource, maxPx, isRtl) {
            detectTapGestures(
                onPress = { pos ->
                    draggableState.drag(MutatePriority.UserInput) {
                        val to = if (isRtl) maxPx - pos.x else pos.x

                        // set position to where pointer is
                        dragBy(to - rawOffset.value)
                    }

                    // add ability to listen interactions for clients
                    // for example press for thumb
                    val interaction = PressInteraction.Press(pos)
                    interactionSource.emit(interaction)
                    val finishInteraction =
                        try {
                            val success = tryAwaitRelease()
                            gestureEndAction.value.invoke(0f)
                            if (success) {
                                PressInteraction.Release(interaction)
                            } else {
                                PressInteraction.Cancel(interaction)
                            }
                        } catch (c: CancellationException) {
                            PressInteraction.Cancel(interaction)
                        }
                    interactionSource.emit(finishInteraction)
                }
            )
        }
    } else {
        this
    }

private fun stepsToTickFractions(steps: Int): List<Float> {
    return if (steps == 0) emptyList() else List(steps + 2) { it.toFloat() / (steps + 1) }
}

// Scale x1 from a1..b1 range to a2..b2 range
private fun scale(a1: Float, b1: Float, x1: Float, a2: Float, b2: Float) =
    lerp(a2, b2, calcFraction(a1, b1, x1))

// Scale x.start, x.endInclusive from a1..b1 range to a2..b2 range
private fun scale(a1: Float, b1: Float, x: ClosedFloatingPointRange<Float>, a2: Float, b2: Float) =
    scale(a1, b1, x.start, a2, b2)..scale(a1, b1, x.endInclusive, a2, b2)

// Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)
