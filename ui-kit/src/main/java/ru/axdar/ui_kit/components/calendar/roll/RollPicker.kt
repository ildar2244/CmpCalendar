package ru.axdar.ui_kit.components.calendar.roll

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.core.theme.CmpAppTheme
import kotlin.math.abs

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun <T> RollPicker(
    modifier: Modifier = Modifier,
    items: Array<T>,
    selectedItem: Int,
    label: (T) -> String = { it.toString() },
    onValueChange: (T) -> Unit,
    selectedItemShape: Shape = RoundedCornerShape(6.dp)
) {
    var position by remember { mutableStateOf(0) }
    var offset by remember { mutableStateOf(0f) }
    val itemHeightPx = with(LocalDensity.current) { 28.dp.toPx() }
    val triggerHeight = 7
    val minimumAlpha = 0.025f
    val fadeInAnim = fadeIn(
        animationSpec = spring(stiffness = Spring.StiffnessHigh),
        initialAlpha = minimumAlpha
    )
    val fadeOutAnim = fadeOut(
        animationSpec = spring(stiffness = Spring.StiffnessHigh),
        targetAlpha = minimumAlpha
    )
    position = (-offset / itemHeightPx + selectedItem).toInt()

    Column(
        modifier = modifier
            .size(160.dp, 172.dp)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta -> offset += delta }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Content(
            topContent = {
                Surface(
                    modifier = Modifier
                        .offset(y = (-4).dp)
                        .size(160.dp, 40.dp),
                    shape = selectedItemShape,
                    color = CmpAppTheme.colors.backgroundSecondary
                ) {}
            },
            bottomContent = {
                AnimatedContent(
                    targetState = position,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInVertically { height -> height / triggerHeight } +
                                    fadeInAnim with
                                    slideOutVertically { height -> height / triggerHeight } +
                                    fadeOutAnim
                        } else {
                            slideInVertically { height -> -height / triggerHeight } +
                                    fadeInAnim with
                                    slideOutVertically { height -> -height / triggerHeight } +
                                    fadeOutAnim
                        }.using(
                            SizeTransform(clip = true)
                        )
                    }
                ) { targetPosition ->
                    Column {
                        Text(
                            modifier = Modifier
                                .size(160.dp, 16.dp)
                                .alpha(0.5f),
                            text = label(getItem(items, targetPosition - 3)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p3Regular
                        )
                        Text(
                            modifier = Modifier
                                .size(160.dp, 20.dp)
                                .alpha(0.7f),
                            text = label(getItem(items, targetPosition - 2)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p2Regular
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .size(160.dp, 24.dp),
                            text = label(getItem(items, targetPosition - 1)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p1Regular
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                                .size(160.dp, 28.dp),
                            text = label(getItem(items, targetPosition)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textPrimary,
                            style = CmpAppTheme.typography.h3Regular
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .size(160.dp, 24.dp),
                            text = label(getItem(items, targetPosition + 1)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p1Regular
                        )
                        Text(
                            modifier = Modifier
                                .size(160.dp, 20.dp)
                                .alpha(0.7f),
                            text = label(getItem(items, targetPosition + 2)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p2Regular
                        )
                        Text(
                            modifier = Modifier
                                .size(160.dp, 60.dp)
                                .alpha(0.5f),
                            text = label(getItem(items, targetPosition + 3)),
                            textAlign = TextAlign.Center,
                            color = CmpAppTheme.colors.textTertiary,
                            style = CmpAppTheme.typography.p3Regular
                        )
                    }
                    try {
                        onValueChange(getItem(items, targetPosition))
                    } catch (e: Exception) {
                        Log.d("9999", "RollPicker: $e \n ${e.message}")
                    }
                }
            }
        )
    }
}

@Composable
private fun Content(
    bottomContent: @Composable () -> Unit,
    topContent: @Composable () -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        topContent()
        bottomContent()
    }
}

private fun <T> getItem(items: Array<T>, position: Int): T {
    return when {
        (position > items.size - 1) -> items[position % items.size]
        (position < 0) -> items[
                if (abs(position) % items.size == 0) 0
                else items.size - (abs(position) % items.size)
        ]
        else -> items[position]
    }
}