package com.thechance.weather.data.repository.weather

import com.thechance.weather.data.mapper.toDomain
import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData
import com.thechance.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getWeather(location: Location): Result<WeatherData> {
        return try {
            val weatherData = weatherRemoteDataSource.getWeatherData(location)
            Result.success(weatherData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}