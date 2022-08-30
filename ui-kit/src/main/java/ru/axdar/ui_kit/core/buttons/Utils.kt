package ru.axdar.ui_kit.core.buttons

import androidx.compose.runtime.Composable
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
internal fun getButtonStyleByButtonType(buttonType: ButtonType) = when (buttonType) {
    ButtonType.SMALL -> CmpAppTheme.buttonStyles.smallButton
    ButtonType.MEDIUM -> CmpAppTheme.buttonStyles.mediumButton
    ButtonType.LARGE -> CmpAppTheme.buttonStyles.largeButton
}
