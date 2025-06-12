package com.thechance.weather.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun WeatherAppTheme(
    useDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) darkColors else lightColors
    CompositionLocalProvider(LocalAppColors provides colors) {
        content()
    }
}


object WeatherTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}
