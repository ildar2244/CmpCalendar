package ru.axdar.ui_kit.core.typography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCmpTypography = staticCompositionLocalOf<CmpTypography> {
    error("No Typography provide")
}

@Composable
internal fun ProvideCmpTypography(
    cmpTypography: CmpTypography,
    content: @Composable () -> Unit,
) {
    val styles = remember {
        cmpTypography
    }
    CompositionLocalProvider(
        values = arrayOf(LocalCmpTypography provides styles),
        content = content
    )
}