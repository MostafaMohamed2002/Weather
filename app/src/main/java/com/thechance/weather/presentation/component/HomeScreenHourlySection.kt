package com.thechance.weather.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R
import com.thechance.weather.presentation.model.HourlyForecastItemState
import com.thechance.weather.presentation.theme.WeatherTheme


@Composable
fun HomeScreenHourlySection(state: List<HourlyForecastItemState>) {
    Text(
        text = "Today",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.urbanist)),
            fontWeight = FontWeight(600),
            color = WeatherTheme.colors.textColor,
            letterSpacing = 0.25.sp,
        ), modifier = Modifier.padding(top = 24.dp, start = 12.dp)
    )

    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = PaddingValues(start = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = state.size,
        ) { index ->
            val hourlyItem = state[index]

            HourlyInfoItem(
                image = painterResource(
                    getWeatherIcon(
                        weatherType = hourlyItem.weatherType,
                        isDay = hourlyItem.isDay
                    )
                ),
                temperature = hourlyItem.temperature,
                time = hourlyItem.time,
                modifier = Modifier.animateItem()
            )
        }
    }

}
