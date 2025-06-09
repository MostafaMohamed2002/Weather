package com.thechance.weather.domain.model


/**
 * The main container for all weather information needed by the UI.
 */
data class WeatherData(
    val currentWeather: CurrentWeather,
    val hourlyForecast: List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>
)