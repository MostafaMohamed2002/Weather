package com.thechance.weather.domain.model


data class WeatherData(
    val currentWeather: CurrentWeather,
    val hourlyForecast: List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>
)