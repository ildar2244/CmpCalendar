package ru.axdar.ui_kit.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
fun CmpCircularProgressIndicatorPrimary(
    modifier: Modifier,
    stroke: Stroke
) = CmpCircularProgressIndicator(
    modifier = modifier,
    stroke = stroke,
    strokeColor = CmpAppTheme.colors.controlsPrimaryActive
)

@Composable
fun CmpCircularProgressIndicatorSecondary(
    modifier: Modifier,
    stroke: Stroke
) = CmpCircularProgressIndicator(
    modifier = modifier,
    stroke = stroke,
    strokeColor = CmpAppTheme.colors.controlsAlternative
)

@Composable
internal fun CmpCircularProgressIndicator(
    modifier: Modifier,
    stroke: Stroke,
    strokeColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier) {
        rotate(rotation) {
            drawArc(
                brush = Brush.sweepGradient(
                    0f to Color.Transparent,
                    .25f to Color.Transparent,
                    1f to strokeColor
                ),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = stroke
            )
        }
    }
}