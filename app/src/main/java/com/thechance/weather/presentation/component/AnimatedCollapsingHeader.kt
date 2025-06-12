import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.thechance.weather.presentation.component.CurrentWeatherTemperature
import com.thechance.weather.presentation.component.animateAsState
import com.thechance.weather.presentation.modifier.dropShadow
import com.thechance.weather.presentation.theme.WeatherTheme


@Composable
fun AnimatedCollapsingHeader(
    isCollapsed: Boolean,
    modifier: Modifier = Modifier,
    temperature: String,
    temperatureDescription: String,
    minTemperature: String,
    maxTemperature: String,
    image: Painter
) {
    val animationValues = animateAsState(isCollapsed = isCollapsed)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(animationValues.boxHeight),
    ) {
        Box(
            modifier = Modifier.align(alignment = animationValues.imageAlignment)

        ) {
            Box(
                modifier = Modifier

                    .size(animationValues.imageSize)
                    .align(alignment = animationValues.imageAlignment)
                    .dropShadow(
                        shape = CircleShape,
                        color = WeatherTheme.colors.primaryColor.copy(alpha = 0.3f),
                        blur = 150.dp,
                    )
            )
            Image(
                painter = image,
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(animationValues.imageSize)
                    .align(Alignment.Center)
            )

        }

        CurrentWeatherTemperature(
            modifier = Modifier.align(alignment = animationValues.weatherAlignment),
            temperature = temperature,
            temperatureDescription = temperatureDescription,
            minTemperature = minTemperature,
            maxTemperature = maxTemperature
        )
    }
}


