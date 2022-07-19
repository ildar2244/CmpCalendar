package ru.axdar.ui_kit.core.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCmpColors = staticCompositionLocalOf<CmpColors> {
    error("No ColorPalette provided")
}

@Composable
internal fun ProvideCmpColors(
    colors: CmpColors,
    content: @Composable () -> Unit,
) {
    val palette = remember {
        colors
    }
    CompositionLocalProvider(
        values = arrayOf(LocalCmpColors provides palette),
        content = content,
    )
}