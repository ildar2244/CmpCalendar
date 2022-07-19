package ru.axdar.ui_kit.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import ru.axdar.ui_kit.core.buttons.CmpButtonStyles
import ru.axdar.ui_kit.core.buttons.LocalCmpButtonStyles
import ru.axdar.ui_kit.core.buttons.ProvideCmpButtonStyles
import ru.axdar.ui_kit.core.debugTypography
import ru.axdar.ui_kit.core.theme.colors.*
import ru.axdar.ui_kit.core.theme.colors.ProvideCmpColors
import ru.axdar.ui_kit.core.theme.colors.lightPalette
import ru.axdar.ui_kit.core.theme.colors.nightPalette
import ru.axdar.ui_kit.core.typography.CmpTypography
import ru.axdar.ui_kit.core.typography.LocalCmpTypography
import ru.axdar.ui_kit.core.typography.ProvideCmpTypography

@Composable
fun CmpAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: CmpTypography = CmpTypography(),
    buttonStyles: CmpButtonStyles = CmpButtonStyles(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) nightPalette else lightPalette

    ProvideCmpTypography(cmpTypography = typography) {
        ProvideCmpColors(colors = colors) {
            ProvideCmpButtonStyles(buttonStyles = buttonStyles) {
                MaterialTheme(
                    colors = debugColors(darkTheme),
                    typography = debugTypography(),
                    shapes = shapes
                ) {
                    ProvideCmpTextSelectionColors(content = content)
                }
            }
        }
    }
}

object CmpAppTheme {
    val colors: CmpColors
        @Composable
        get() = LocalCmpColors.current
    val typography: CmpTypography
        @Composable
        get() = LocalCmpTypography.current
    val buttonStyles: CmpButtonStyles
        @Composable
        get() = LocalCmpButtonStyles.current
}