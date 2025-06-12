package com.thechance.weather.ui.component

import AnimatedCollapsingHeader
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.weather.ui.model.HeaderState


@Composable
fun HomeScreenHeaderSection(isCollapsed: Boolean, headerState: HeaderState) {
    LocationInfo(
        location = headerState.cityName,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 24.dp)
    )
    AnimatedCollapsingHeader(
        isCollapsed = isCollapsed,
        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
        temperature = headerState.temperature,
        temperatureDescription = headerState.weatherDescription,
        minTemperature = headerState.lowTemp,
        maxTemperature = headerState.highTemp,
        image = painterResource(
            getWeatherIcon(
                headerState.weatherType,
                isDay = headerState.isDay
            )
        ),
    )
}