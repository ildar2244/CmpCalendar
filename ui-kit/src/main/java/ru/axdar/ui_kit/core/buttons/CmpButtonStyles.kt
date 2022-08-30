package ru.axdar.ui_kit.core.buttons

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.core.cmpSans

class CmpButtonStyles(
    val smallButton: CmpButtonStyle,
    val mediumButton: CmpButtonStyle,
    val largeButton: CmpButtonStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = cmpSans,
        smallButton: CmpButtonStyle = CmpButtonStyle(
            height = 32.dp,
            corners = 6.dp,
            iconSize = 10.dp,
            progressSize = 14.dp,
            progressStrokeWidth = 3f,
        ),
        mediumButton: CmpButtonStyle = CmpButtonStyle(
            height = 44.dp,
            corners = 8.dp,
            iconSize = 16.dp,
            progressSize = 22.dp,
            progressStrokeWidth = 5f,
        ),
        largeButton: CmpButtonStyle = CmpButtonStyle(
            height = 52.dp,
            corners = 8.dp,
            iconSize = 16.dp,
            progressSize = 22.dp,
            progressStrokeWidth = 5f,
        ),
    ) : this(
        smallButton = smallButton,
        mediumButton = mediumButton,
        largeButton = largeButton,
    )
}