package ru.axdar.ui_kit.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ru.axdar.ui_kit.core.buttons.ButtonType
import ru.axdar.ui_kit.core.buttons.CmpButton
import ru.axdar.ui_kit.core.buttons.CmpButtonColors
import ru.axdar.ui_kit.core.buttons.ContentColor
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
@ExperimentalComposeUiApi
fun CmpButtonAlternative(
    modifier: Modifier = Modifier,
    contentColor: ContentColor = cmpButtonAlternativeColor,
    buttonWidth: Dp = Dp.Unspecified,
    buttonType: ButtonType = ButtonType.SMALL,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = CmpButtonColors(
        backgroundColor = CmpAppTheme.colors.controlsAlternative,
        contentColor = contentColor.color(),
        disableBackgroundColor = CmpAppTheme.colors.controlsInactive,
        disableContentColor = CmpAppTheme.colors.controlsContentInactive,
    )

    CmpButton(
        modifier = modifier,
        buttonWidth = buttonWidth,
        buttonType = buttonType,
        colors = colors,
        progressColor = contentColor.color(),
        enabled = enabled,
        loading = loading,
        onClick = onClick,
        content = content
    )
}

internal val cmpButtonAlternativeColor = object : ContentColor {
    @Composable
    override fun color(): Color {
        return CmpAppTheme.colors.controlsContentAlternative
    }
}