package ru.axdar.ui_kit.core.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.axdar.ui_kit.core.cmpSans

@Immutable
class CmpTypography(
    val h1Black: TextStyle,
    val h1Bold: TextStyle,
    val h2Black: TextStyle,
    val h2Bold: TextStyle,
    val h2Medium: TextStyle,
    val h3Bold: TextStyle,
    val h3Medium: TextStyle,
    val h3Regular: TextStyle,
    val p1Bold: TextStyle,
    val p1Medium: TextStyle,
    val p1Regular: TextStyle,
    val p2Bold: TextStyle,
    val p2Medium: TextStyle,
    val p2Regular: TextStyle,
    val p2MediumUpperCase: TextStyle,
    val p3Bold: TextStyle,
    val p3Medium: TextStyle,
    val p3Regular: TextStyle,
    val p3MediumUpperCase: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = cmpSans,
        h1Black: TextStyle = TextStyle(
            fontWeight = FontWeight.Black,
            fontSize = 32.sp,
            lineHeight = 36.sp
        ),
        h1Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 36.sp
        ),
        h2Black: TextStyle = TextStyle(
            fontWeight = FontWeight.Black,
            fontSize = 24.sp,
            lineHeight = 28.sp
        ),
        h2Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 28.sp
        ),
        h2Medium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 28.sp
        ),
        h3Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),
        h3Medium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),
        h3Regular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        p1Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        p1Medium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        p1Regular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        p2Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        p2Medium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        p2Regular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        p2MediumUpperCase: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        p3Bold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        p3Medium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        p3Regular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        p3MediumUpperCase: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
    ) : this(
        h1Black = h1Black.withDefaultFontFamily(defaultFontFamily),
        h1Bold = h1Bold.withDefaultFontFamily(defaultFontFamily),
        h2Black = h2Black.withDefaultFontFamily(defaultFontFamily),
        h2Bold = h2Bold.withDefaultFontFamily(defaultFontFamily),
        h2Medium = h2Medium.withDefaultFontFamily(defaultFontFamily),
        h3Bold = h3Bold.withDefaultFontFamily(defaultFontFamily),
        h3Medium = h3Medium.withDefaultFontFamily(defaultFontFamily),
        h3Regular = h3Regular.withDefaultFontFamily(defaultFontFamily),
        p1Bold = p1Bold.withDefaultFontFamily(defaultFontFamily),
        p1Medium = p1Medium.withDefaultFontFamily(defaultFontFamily),
        p1Regular = p1Regular.withDefaultFontFamily(defaultFontFamily),
        p2Bold = p2Bold.withDefaultFontFamily(defaultFontFamily),
        p2Medium = p2Medium.withDefaultFontFamily(defaultFontFamily),
        p2Regular = p2Regular.withDefaultFontFamily(defaultFontFamily),
        p2MediumUpperCase = p2MediumUpperCase.withDefaultFontFamily(defaultFontFamily),
        p3Bold = p3Bold.withDefaultFontFamily(defaultFontFamily),
        p3Medium = p3Medium.withDefaultFontFamily(defaultFontFamily),
        p3Regular = p3Regular.withDefaultFontFamily(defaultFontFamily),
        p3MediumUpperCase = p3MediumUpperCase.withDefaultFontFamily(defaultFontFamily),
    )
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}