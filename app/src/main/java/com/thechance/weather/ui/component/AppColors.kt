import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class AppColors(
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
