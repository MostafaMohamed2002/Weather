package com.thechance.weather.domain.model

import java.time.LocalDate

/**
 * Represents the data for a single row in the "Next 7 days" forecast.
 */
data class DailyForecast(
    val day: LocalDate,
    val minTemperature: Double,
    val maxTemperature: Double,
    val weatherType: WeatherType
)