package com.thechance.weather.domain.model

import java.time.LocalDateTime


data class HourlyForecast(
    val time: LocalDateTime,
    val temperature: Double,
    val weatherType: WeatherType,
    val isDay: Boolean
)