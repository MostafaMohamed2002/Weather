package com.thechance.weather.data.mapper


import com.thechance.weather.data.remote.dto.weather.Current
import com.thechance.weather.data.remote.dto.weather.Daily
import com.thechance.weather.data.remote.dto.weather.Hourly
import com.thechance.weather.data.remote.dto.weather.WeatherResponseDto
import com.thechance.weather.domain.model.CurrentWeather
import com.thechance.weather.domain.model.DailyForecast
import com.thechance.weather.domain.model.HourlyForecast
import com.thechance.weather.domain.model.WeatherData
import com.thechance.weather.domain.model.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherResponseDto.toDomain(): WeatherData {
    return WeatherData(
        currentWeather = current.toDomain(daily),
        hourlyForecast = hourly.toDomain(),
        dailyForecast = daily.toDomain()
    )
}

private fun Current.toDomain(dailyDto: Daily): CurrentWeather {
    val todayMinTemp = dailyDto.minTemperatures.firstOrNull() ?: 0.0
    val todayMaxTemp = dailyDto.maxTemperatures.firstOrNull() ?: 0.0

    return CurrentWeather(
        temperature = this.temperature,
        feelsLike = this.apparentTemperature,
        pressure = this.pressureMsl,
        windSpeed = this.windSpeed,
        humidity = this.relativeHumidity,
        uvIndex = this.uvIndex,
        rainAmount = this.rain,
        weatherType = WeatherType.fromWmoCode(this.weatherCode),
        dailyMinTemp = todayMinTemp,
        dailyMaxTemp = todayMaxTemp,
        isDay = this.isDay == 1
    )
}


private fun Hourly.toDomain(): List<HourlyForecast> {
    return time.mapIndexed { index, timeString ->
        HourlyForecast(
            time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            temperature = this.temperatures[index],
            weatherType = WeatherType.fromWmoCode(this.weatherCodes[index]),
            isDay = this.isDay[index] == 1
        )
    }
}


private fun Daily.toDomain(): List<DailyForecast> {
    return time.mapIndexed { index, dateString ->
        DailyForecast(
            day = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE),
            maxTemperature = this.maxTemperatures[index],
            minTemperature = this.minTemperatures[index],
            weatherType = WeatherType.fromWmoCode(this.weatherCodes[index])
        )
    }
}

