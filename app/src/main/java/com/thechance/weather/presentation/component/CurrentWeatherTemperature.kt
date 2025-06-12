package com.thechance.weather.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.thechance.weather.presentation.theme.WeatherTheme

@Composable
fun CurrentWeatherTemperature(
    modifier: Modifier = Modifier,
    temperature: String,
    temperatureDescription: String,
    minTemperature: String,
    maxTemperature: String
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$temperature",
            style = TextStyle(
                fontSize = 64.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(600),
                color = WeatherTheme.colors.textColor,
                textAlign = TextAlign.Center,
                letterSpacing = 0.25.sp,
            )
        )
        Text(
            text = temperatureDescription,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(500),
                color = WeatherTheme.colors.textColor60,
                textAlign = TextAlign.Center,
                letterSpacing = 0.25.sp,
            )
        )
        Spacer(Modifier.size(12.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(WeatherTheme.colors.lightContainerColor)
                .padding(
                    start = 24.dp, end = 24.dp,
                    top = 8.dp, bottom = 8.dp
                )
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_up),
                    contentDescription = null,
                    tint = WeatherTheme.colors.textColor60,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = "${maxTemperature}°C",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist)),
                        fontWeight = FontWeight(500),
                        color = WeatherTheme.colors.textColor60,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.25.sp,
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.line_1),
                    contentDescription = null,
                    tint = WeatherTheme.colors.textColor60,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = null,
                    tint = WeatherTheme.colors.textColor60,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = "${minTemperature}°C",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist)),
                        fontWeight = FontWeight(500),
                        color = WeatherTheme.colors.textColor60,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CurrentWeatherTemperaturePreview() {
    CurrentWeatherTemperature(
        temperature = "25",
        temperatureDescription = "Partly cloudy",
        minTemperature = "20",
        maxTemperature = "30"
    )
}


