package com.thechance.weather.data.repository.weather

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData

interface WeatherRemoteDataSource {
    suspend fun getWeatherData(location: Location): WeatherData
}