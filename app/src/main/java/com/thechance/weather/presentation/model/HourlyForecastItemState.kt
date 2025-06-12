package com.thechance.weather.presentation.model

import com.thechance.weather.domain.model.WeatherType

data class HourlyForecastItemState(
    val time: String,
    val temperature: String,
    val weatherType: WeatherType,
    val isDay: Boolean
)
