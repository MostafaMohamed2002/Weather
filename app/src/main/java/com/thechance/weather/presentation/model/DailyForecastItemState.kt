package com.thechance.weather.presentation.model

import com.thechance.weather.domain.model.WeatherType

data class DailyForecastItemState(
    val day: String,
    val highTemp: String,
    val lowTemp: String,
    val weatherType: WeatherType
)
