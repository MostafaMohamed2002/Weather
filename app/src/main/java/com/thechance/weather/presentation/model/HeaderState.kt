package com.thechance.weather.presentation.model

import com.thechance.weather.domain.model.WeatherType

data class HeaderState(
    val cityName: String,
    val temperature: String,
    val weatherDescription: String,
    val highTemp: String,
    val lowTemp: String,
    val weatherType: WeatherType,
    val isDay: Boolean
)