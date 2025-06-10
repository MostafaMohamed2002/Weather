package com.thechance.weather.ui.component

import WeatherAppTheme
import WeatherTheme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.weather.R
import com.thechance.weather.domain.model.CurrentWeather
import kotlin.math.roundToInt

/**
 * A simple data class to hold the exact information needed to display
 * one item in the weather details grid.
 */
data class WeatherDetailItemModel(
    @DrawableRes val iconRes: Int,
    val formattedValue: String,
    val label: String
)

/**
 * A composable that displays the 2x3 grid of weather details.
 * It takes the CurrentWeather domain model and maps its properties
 * to the specific UI models needed by the WeatherInfoItem.
 */
@Composable
fun WeatherDetailsGrid(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    // This is the mapping step. We convert the raw domain data into a list
    // of UI models that perfectly match what our WeatherInfoItem needs.
    val weatherDetails = listOf(
        WeatherDetailItemModel(
            iconRes = R.drawable.fast_wind,
            formattedValue = "${currentWeather.windSpeed} km/h",
            label = "Wind"
        ),
        WeatherDetailItemModel(
            iconRes = R.drawable.humidity,
            formattedValue = "${currentWeather.humidity}%",
            label = "Humidity"
        ),
        WeatherDetailItemModel(
            iconRes = R.drawable.rain,
            formattedValue = "${currentWeather.rainAmount.roundToInt()}%", // Assuming rain is a probability
            label = "Rain"
        ),
        WeatherDetailItemModel(
            iconRes = R.drawable.uv,
            formattedValue = "${currentWeather.uvIndex.roundToInt()}",
            label = "UV Index"
        ),
        WeatherDetailItemModel(
            iconRes = R.drawable.pressure,
            formattedValue = "${currentWeather.pressure.roundToInt()} hPa",
            label = "Pressure"
        ),
        WeatherDetailItemModel(
            iconRes = R.drawable.temperature,
            formattedValue = "${currentWeather.feelsLike.roundToInt()}°C",
            label = "Feels like"
        )
    )

    // Now we lay out the data in a grid
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Take the first 3 items from our list
            weatherDetails.take(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.formattedValue,
                    label = detail.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Take items 4, 5, and 6 from our list
            weatherDetails.drop(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.formattedValue,
                    label = detail.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Preview
@Composable
fun WeatherDetailsGridPreview() {
    // Create fake data to render the preview
    val fakeCurrentWeather = CurrentWeather(
        temperature = 24.0,
        feelsLike = 22.0,
        pressure = 1012.0,
        windSpeed = 13.0,
        humidity = 24,
        uvIndex = 2.0,
        rainAmount = 2.0,
        weatherType = com.thechance.weather.domain.model.WeatherType.PartlyCloudy,
        dailyMinTemp = 20.0,
        dailyMaxTemp = 32.0,
        isDay = !isSystemInDarkTheme()
    )
    WeatherAppTheme(useDarkTheme = !isSystemInDarkTheme()) {
        Column(Modifier
            .background(WeatherTheme.colors.backgroundBrush)
            .padding(16.dp)) {
            WeatherDetailsGrid(currentWeather = fakeCurrentWeather)
        }
    }
}
