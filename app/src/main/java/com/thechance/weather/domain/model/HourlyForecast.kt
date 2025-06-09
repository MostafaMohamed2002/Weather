package com.thechance.weather.domain.model

import java.time.LocalDateTime

/**
 * Represents the data for a single card in the "Today" hourly forecast.
 */
data class HourlyForecast(
    val time: LocalDateTime,
    val temperature: Double,
    val weatherType: WeatherType,
    val isDay: Boolean
)