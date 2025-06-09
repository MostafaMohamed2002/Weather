package com.thechance.weather.domain.model


/**
 * Represents the data for the main card and the details grid.
 * Notice it includes min/max temp for today, which comes from the 'daily' API data.
 */
data class CurrentWeather(
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Int,
    val uvIndex: Double,
    val rainAmount: Double,
    val weatherType: WeatherType,
    val dailyMinTemp: Double,
    val dailyMaxTemp: Double,
    val isDay: Boolean
)