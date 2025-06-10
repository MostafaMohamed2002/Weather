import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.thechance.weather.ui.component.CurrentWeatherTemperature
import com.thechance.weather.ui.component.animateAsState


@Composable
fun AnimationCollapsingScreen(
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
            .height(animationValues.boxHeight)

    ) {
        Image(
            painter = image,
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(alignment = animationValues.imageAlignment)
                .size(animationValues.imageSize)
        )

        CurrentWeatherTemperature(
            modifier = Modifier
                .align(alignment = animationValues.weatherAlignment),
            temperature = temperature,
            temperatureDescription = temperatureDescription,
            minTemperature = minTemperature,
            maxTemperature = maxTemperature
        )
    }
}


