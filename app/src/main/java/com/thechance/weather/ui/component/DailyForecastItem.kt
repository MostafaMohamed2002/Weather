package com.thechance.weather.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.thechance.weather.domain.model.DailyForecast
import com.thechance.weather.domain.model.WeatherType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun DailyForecastItem(
    modifier: Modifier = Modifier,
    dailyForecast: DailyForecast,
    isDay: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = dailyForecast.day.format(DateTimeFormatter.ofPattern("EEEE")),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(400),
                color = WeatherTheme.colors.textColor60,
                letterSpacing = 0.25.sp,
            ), modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    getWeatherIcon(
                        dailyForecast.weatherType,
                        isDay = isDay
                    )
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(45.dp)
                    .width(91.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_up),
                contentDescription = null,
                tint = WeatherTheme.colors.textColor60,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = "${dailyForecast.maxTemperature.roundToInt()}°C",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
                    color = WeatherTheme.colors.textColor87,
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
                text = "${dailyForecast.minTemperature.roundToInt()}°C",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
                    color = WeatherTheme.colors.textColor87,

                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
        }
    }
}

@Preview
@Composable
fun DailyForecastItemPreview() {
    val dailyForecast = DailyForecast(
        day = LocalDate.now(),
        minTemperature = 10.0,
        maxTemperature = 20.0,
        weatherType = WeatherType.PartlyCloudy
    )
    DailyForecastItem(dailyForecast = dailyForecast)
}
