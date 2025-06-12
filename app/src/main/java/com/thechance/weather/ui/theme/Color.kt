package com.thechance.weather.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryColor: Color = Color.Unspecified,
    val backgroundBrush: Brush,
    val lightContainerColor: Color,
    val borderColor: Color,
    val textColor: Color,
    val textColor87: Color,
    val containerColor: Color,
    val textColor60: Color,
    val locationTextColor: Color
)

val darkColors = AppColors(
    primaryColor = Color(0xFFC0B7FF),
    backgroundBrush = Brush.verticalGradient(
        listOf(
            Color(0xFF060414),
            Color(0xFF0D0C19),
        )
    ),
    textColor = Color(0xFFFFFFFF),
    textColor87 = Color(0xDEFFFFFF),
    containerColor = Color(0xB3060414),
    textColor60 = Color(0x99FFFFFF),
    lightContainerColor = Color(0x14FFFFFF),
    borderColor = Color(0x14FFFFFF),
    locationTextColor = Color(0xFFFFFFFF),
)

val lightColors = AppColors(
    primaryColor = Color(0xFF00619D),

    backgroundBrush = Brush.verticalGradient(
        listOf(
            Color(0xFF87CEFA),
            Color(0xFFFFFFFF),
        )
    ),
    textColor = Color(0xFF060414),
    textColor87 = Color(0xDE060414),
    containerColor = Color(0xB3FFFFFF),
    textColor60 = Color(0x99060414),
    lightContainerColor = Color(0x14060414),
    borderColor = Color(0x14060414),
    locationTextColor = Color(0xFF323232),
)


val LocalAppColors = staticCompositionLocalOf {
    lightColors
}
