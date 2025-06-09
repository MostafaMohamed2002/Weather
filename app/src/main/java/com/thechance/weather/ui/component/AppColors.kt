import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// --- 1. Define your Custom Colors data class and instances ---

// A data class to hold all the colors for a specific theme (day or night)
data class AppColors(
    val backgroundBrush: Brush,
    val lightContainerColor: Color,
    val borderColor: Color,
    val textColor: Color,
    val textColor87: Color,
    val containerColor: Color,
    val textColor60: Color
)

// The definition for your dark (night) theme colors
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
    borderColor = Color(0x14FFFFFF)
)

// The definition for your light (day) theme colors
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
    borderColor = Color(0x14060414)
)


// --- 2. Create the CompositionLocal ---
// This is the mechanism to pass your color theme down the composition tree.
val LocalAppColors = staticCompositionLocalOf {
    // Provide the lightColors as a default.
    // This default is rarely used because WeatherAppTheme provides a specific value.
    lightColors
}


// --- 3. The Main Theme Composable ---

@Composable
fun WeatherAppTheme(
    useDarkTheme: Boolean, // No default value; the caller MUST decide.
    content: @Composable () -> Unit
) {
    // Select the correct set of colors based on the is_day value
    val colors = if (useDarkTheme) darkColors else lightColors

    // Provide the selected colors to all the composables below using CompositionLocalProvider.
    // Any composable inside the 'content' block can now access these colors.
    CompositionLocalProvider(LocalAppColors provides colors) {
        content()
    }
}


// --- 4. Create a custom theme object for easy access ---
// This is a convenient way to access your colors from any composable.
// Usage example: `WeatherTheme.colors.backgroundBrush`
object WeatherTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}
