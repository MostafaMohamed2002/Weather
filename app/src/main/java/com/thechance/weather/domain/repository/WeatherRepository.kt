package com.thechance.weather.domain.repository

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeather(location: Location): Result<WeatherData>
}