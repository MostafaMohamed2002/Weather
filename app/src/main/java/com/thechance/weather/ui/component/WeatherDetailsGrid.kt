package com.thechance.weather.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R
import com.thechance.weather.domain.model.CurrentWeather
import com.thechance.weather.domain.model.WeatherType
import kotlin.math.roundToInt


data class WeatherDetailItemModel(
    @DrawableRes val iconRes: Int,
    val formattedValue: String,
    val label: String
)


@Composable
fun WeatherInfoItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    formattedValue: String,
    label: String,
) {
    Box(
        modifier
            .width(108.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(WeatherTheme.colors.containerColor)
            .border(
                width = 1.dp,
                color = WeatherTheme.colors.borderColor,
                shape = RoundedCornerShape(size = 24.dp)
            ), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = label,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(24.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = formattedValue,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
                    color = WeatherTheme.colors.textColor87,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(400),
                    color = WeatherTheme.colors.textColor60,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
        }
    }
}



@Composable
fun WeatherDetailsGrid(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    val weatherDetails = prepareWeatherDetails(currentWeather)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            weatherDetails.take(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.formattedValue,
                    label = detail.label,
                    modifier = Modifier.weight(1f) // Use weight to distribute space evenly
                )
            }
        }
        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            weatherDetails.drop(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.formattedValue,
                    label = detail.label,
                    modifier = Modifier.weight(1f) // Use weight to distribute space evenly
                )
            }
        }
    }
}

/**
 * A helper function to map the domain model [CurrentWeather] into a list of
 * UI-ready [WeatherDetailItemModel]s. This keeps the main composable clean.
 */
@Composable
private fun prepareWeatherDetails(currentWeather: CurrentWeather): List<WeatherDetailItemModel> {
    return remember(currentWeather) {
        listOf(
            WeatherDetailItemModel(
                iconRes = R.drawable.fast_wind,
                formattedValue = "${currentWeather.windSpeed.roundToInt()} KM/h",
                label = "Wind"
            ),
            WeatherDetailItemModel(
                iconRes = R.drawable.humidity,
                formattedValue = "${currentWeather.humidity}%",
                label = "Humidity"
            ),
            WeatherDetailItemModel(
                iconRes = R.drawable.rain,
                formattedValue = "${currentWeather.rainAmount.roundToInt()}%",
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
    }
}


@Preview
@Composable
fun WeatherDetailsGridPreview() {
    val fakeCurrentWeather = CurrentWeather(
        temperature = 24.0,
        feelsLike = 22.0,
        pressure = 1012.0,
        windSpeed = 13.0,
        humidity = 24,
        uvIndex = 2.0,
        rainAmount = 2.0,
        weatherType = WeatherType.PartlyCloudy,
        dailyMinTemp = 20.0,
        dailyMaxTemp = 32.0,
        isDay = !isSystemInDarkTheme()
    )
    WeatherAppTheme(useDarkTheme = !isSystemInDarkTheme()) {
        Column(
            Modifier
                .background(WeatherTheme.colors.backgroundBrush)
                .padding(16.dp)
        ) {
            WeatherDetailsGrid(currentWeather = fakeCurrentWeather)
        }
    }
}
