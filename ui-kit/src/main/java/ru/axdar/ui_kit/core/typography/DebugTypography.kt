package ru.axdar.ui_kit.core

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun debugTypography(
    debugTextStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.W100,
        fontSize = 10.sp
    )
) = Typography(
    defaultFontFamily = cmpSans,
    h1 = debugTextStyle,
    h2 = debugTextStyle,
    h3 = debugTextStyle,
    h4 = debugTextStyle,
    h5 = debugTextStyle,
    h6 = debugTextStyle,
    subtitle1 = debugTextStyle,
    subtitle2 = debugTextStyle,
    body1 = debugTextStyle,
    body2 = debugTextStyle,
    button = debugTextStyle,
    caption = debugTextStyle,
    overline = debugTextStyle,
)