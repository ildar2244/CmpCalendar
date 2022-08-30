package ru.axdar.ui_kit.core.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.components.CmpCircularProgressIndicator

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
fun CmpButton(
    modifier: Modifier = Modifier,
    buttonWidth: Dp = Dp.Unspecified,
    buttonType: ButtonType = ButtonType.SMALL,
    colors: CmpButtonColors,
    progressColor: Color,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressedState = interactionSource
        .collectIsPressedAsState()
        .value

    val scale by animateFloatAsState(if (pressedState) 0.96f else 1f)

    val style = getButtonStyleByButtonType(buttonType)

    val resultedModifier = modifier
        .width(buttonWidth)
        .height(style.height)
        .scale(scale)

    val loadingContent: @Composable RowScope.() -> Unit = {
        CmpCircularProgressIndicator(
            modifier = Modifier.size(style.progressSize),
            stroke = Stroke(style.progressStrokeWidth),
            strokeColor = progressColor
        )
    }

    val contentColor by colors.contentColor(enabled)

    Surface(
        modifier = resultedModifier,
        shape = RoundedCornerShape(style.corners),
        color = colors.backgroundColor(enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = null,
        elevation = 0.dp,
        onClick = onClick,
        enabled = enabled,
        role = Role.Button,
        interactionSource = interactionSource,
        indication = null
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                )
                .padding(ButtonDefaults.ContentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = if (loading) loadingContent else content
        )
    }
}