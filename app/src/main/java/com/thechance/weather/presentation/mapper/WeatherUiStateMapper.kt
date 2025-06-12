package com.thechance.weather.presentation.mapper

import com.thechance.weather.R
import com.thechance.weather.domain.model.CurrentWeather
import com.thechance.weather.domain.model.DailyForecast
import com.thechance.weather.domain.model.HourlyForecast
import com.thechance.weather.domain.model.WeatherData
import com.thechance.weather.presentation.model.DailyForecastItemState
import com.thechance.weather.presentation.model.DetailItemState
import com.thechance.weather.presentation.model.HeaderState
import com.thechance.weather.presentation.model.HourlyForecastItemState
import com.thechance.weather.presentation.model.WeatherUiState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun WeatherData.toUiState(cityName: String): WeatherUiState {
    return WeatherUiState(
        isLoading = false,
        headerState = this.currentWeather.toHeaderState(cityName),
        detailsState = this.currentWeather.toDetailsState(),
        hourlyForecastState = this.hourlyForecast.toHourlyForecastItemState(),
        dailyForecastState = this.dailyForecast.toDailyForecastItemState()
    )
}


private fun CurrentWeather.toHeaderState(cityName: String): HeaderState {
    return HeaderState(
        cityName = cityName,
        temperature = "${this.temperature.roundToInt()}°C",
        weatherDescription = this.weatherType.description,
        highTemp = "${this.dailyMaxTemp.roundToInt()}",
        lowTemp = "${this.dailyMinTemp.roundToInt()}",
        weatherType = this.weatherType,
        isDay = this.isDay
    )
}


private fun CurrentWeather.toDetailsState(): List<DetailItemState> {
    return listOf(
        DetailItemState(R.drawable.fast_wind, "${this.windSpeed.roundToInt()} KM/H", "Wind"),
        DetailItemState(R.drawable.humidity, "${this.humidity}%", "Humidity"),
        DetailItemState(R.drawable.rain, "${this.rainAmount.roundToInt()}%", "Rain"),
        DetailItemState(R.drawable.uv, "${this.uvIndex.roundToInt()}", "UV"),
        DetailItemState(R.drawable.pressure, "${this.pressure.roundToInt()} hPa", "Pressure"),
        DetailItemState(R.drawable.temperature, "${this.feelsLike.roundToInt()}°", "Feels like")
    )
}


private fun List<HourlyForecast>.toHourlyForecastItemState(): List<HourlyForecastItemState> {
    return this.map { hourly ->
        HourlyForecastItemState(
            time = hourly.time.format(DateTimeFormatter.ofPattern("ha")), // e.g., "5PM"
            temperature = "${hourly.temperature.roundToInt()}°C",
            weatherType = hourly.weatherType,
            isDay = hourly.isDay
        )
    }
}


private fun List<DailyForecast>.toDailyForecastItemState(): List<DailyForecastItemState> {
    return this.map { daily ->
        DailyForecastItemState(
            day = daily.day.format(DateTimeFormatter.ofPattern("EEEE")),
            highTemp = "${daily.maxTemperature.roundToInt()}°C",
            lowTemp = "${daily.minTemperature.roundToInt()}°C",
            weatherType = daily.weatherType
        )
    }
}
