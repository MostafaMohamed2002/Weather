package com.thechance.weather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R
import com.thechance.weather.ui.model.DailyForecastItemState
import com.thechance.weather.ui.theme.WeatherTheme


@Composable
fun HomeScreenDailySection(state: List<DailyForecastItemState>) {
    Text(
        text = "Next 7 days",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.urbanist)),
            fontWeight = FontWeight(600),
            color = WeatherTheme.colors.textColor,
            letterSpacing = 0.25.sp,
        ),
        modifier = Modifier.padding(start = 12.dp, top = 24.dp)
    )
    Box(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = WeatherTheme.colors.containerColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = WeatherTheme.colors.borderColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(top = 4.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.forEachIndexed { index, forecast ->
                DailyForecastItem(
                    state = forecast
                )
                if (index < state.size - 1) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = WeatherTheme.colors.borderColor
                    )
                }
            }
        }
    }

}