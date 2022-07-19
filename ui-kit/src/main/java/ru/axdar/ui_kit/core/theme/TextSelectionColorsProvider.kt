package ru.axdar.ui_kit.core.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import ru.axdar.ui_kit.core.theme.colors.CmpRed

@Composable
internal fun ProvideCmpTextSelectionColors(
    content: @Composable () -> Unit
) {
    val textStyles = remember {
        customTestSelectionColors
    }
    CompositionLocalProvider(
        values = arrayOf(LocalTextSelectionColors provides textStyles),
        content = content
    )
}

private val customTestSelectionColors = TextSelectionColors(
    handleColor = CmpRed,
    backgroundColor = CmpRed.copy(alpha = 0.4f)
)