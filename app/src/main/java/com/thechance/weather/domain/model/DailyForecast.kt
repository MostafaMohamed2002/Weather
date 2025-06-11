package com.thechance.weather.domain.model

import java.time.LocalDate


data class DailyForecast(
    val day: LocalDate,
    val minTemperature: Double,
    val maxTemperature: Double,
    val weatherType: WeatherType
)