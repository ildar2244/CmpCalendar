package ru.axdar.ui_kit.core.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCmpButtonStyles = staticCompositionLocalOf<CmpButtonStyles> {
    error("No Typography provided")
}

@Composable
internal fun ProvideCmpButtonStyles(
    buttonStyles: CmpButtonStyles,
    content: @Composable () -> Unit,
) {
    val styles = remember {
        buttonStyles
    }
    CompositionLocalProvider(
        values = arrayOf(LocalCmpButtonStyles provides styles),
        content = content
    )
}