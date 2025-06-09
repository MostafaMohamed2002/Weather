package com.thechance.weather.data.mapper


import com.thechance.weather.data.remote.dto.weather.Current
import com.thechance.weather.data.remote.dto.weather.Daily
import com.thechance.weather.data.remote.dto.weather.Hourly
import com.thechance.weather.data.remote.dto.weather.WeatherResponseDto
import com.thechance.weather.domain.model.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * The main mapper that converts the entire API response DTO into our main WeatherData domain model.
 */
fun WeatherResponseDto.toDomain(): WeatherData {
    return WeatherData(
        currentWeather = current.toDomain(daily), // Pass the 'daily' DTO
        hourlyForecast = hourly.toDomain(),
        dailyForecast = daily.toDomain()
    )
}

/**
 * Converts the 'Current' DTO into the CurrentWeather domain model.
 */
private fun Current.toDomain(dailyDto: Daily): CurrentWeather {
    // Get today's min and max temperatures from the first entry in the daily forecast
    val todayMinTemp = dailyDto.temperature2mMin.firstOrNull() ?: 0.0
    val todayMaxTemp = dailyDto.temperature2mMax.firstOrNull() ?: 0.0

    return CurrentWeather(
        temperature = temperature2m,
        feelsLike = apparentTemperature,
        pressure = pressureMsl,
        windSpeed = windSpeed10m,
        humidity = relativeHumidity2m,
        uvIndex = uvIndex, // Add 'uv_index' to your API request and DTO for this
        rainAmount = rain.toDouble(), // Convert Int to Double
        weatherType = WeatherType.fromWmoCode(weatherCode),
        dailyMinTemp = todayMinTemp,
        dailyMaxTemp = todayMaxTemp,
        isDay = this.isDay == 1 // Convert Int to Boolean
    )
}

/**
 * Converts the 'Hourly' DTO into a list of HourlyForecast domain models.
 */
private fun Hourly.toDomain(): List<HourlyForecast> {
    return time.mapIndexed { index, timeString ->
        HourlyForecast(
            time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            temperature = temperature2m[index],
            weatherType = WeatherType.fromWmoCode(weatherCode[index]),
            isDay = this.isDay[index] == 1 // Convert Int from the list to Boolean,
        )
    }
}

/**
 * Converts the 'Daily' DTO into a list of DailyForecast domain models.
 */
private fun Daily.toDomain(): List<DailyForecast> {
    return time.mapIndexed { index, dateString ->
        DailyForecast(
            day = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE),
            maxTemperature = temperature2mMax[index],
            minTemperature = temperature2mMin[index],
            weatherType = WeatherType.fromWmoCode(weatherCode[index])
        )
    }
}



