package com.thechance.weather.domain.usecase

import com.thechance.weather.domain.model.WeatherDetails
import com.thechance.weather.domain.repository.GeocodingRepository
import com.thechance.weather.domain.repository.LocationRepository
import com.thechance.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetWeatherForCurrentLocationUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(): Result<WeatherDetails> {
        return try {
            val location = locationRepository.getCurrentLocation().getOrThrow()

            coroutineScope {
                val weatherResult = async { weatherRepository.getWeather(location) }
                val cityResult = async { geocodingRepository.getLocationAddress(location) }

                val weatherData = weatherResult.await().getOrThrow()
                val locationAddress = cityResult.await().getOrThrow()

                Result.success(WeatherDetails(weatherData, locationAddress))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}