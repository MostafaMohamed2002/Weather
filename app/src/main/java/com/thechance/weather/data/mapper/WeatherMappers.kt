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
    val todayMinTemp = dailyDto.temperature2mMin.firstOrNull() ?: 0.0
    val todayMaxTemp = dailyDto.temperature2mMax.firstOrNull() ?: 0.0

    return CurrentWeather(
        temperature = temperature2m,
        feelsLike = apparentTemperature,
        pressure = pressureMsl,
        windSpeed = windSpeed10m,
        humidity = relativeHumidity2m,
        uvIndex = uvIndex,
        rainAmount = rain.toDouble(),
        weatherType = WeatherType.fromWmoCode(weatherCode),
        dailyMinTemp = todayMinTemp,
        dailyMaxTemp = todayMaxTemp,
        isDay = this.isDay == 1
    )
}

private fun Hourly.toDomain(): List<HourlyForecast> {
    return time.mapIndexed { index, timeString ->
        HourlyForecast(
            time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            temperature = temperature2m[index],
            weatherType = WeatherType.fromWmoCode(weatherCode[index]),
            isDay = this.isDay[index] == 1
        )
    }
}


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



