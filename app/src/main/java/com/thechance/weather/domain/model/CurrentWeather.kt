package com.thechance.weather.domain.model


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