package com.thechance.weather.domain.usecase

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData
import com.thechance.weather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(location: Location): WeatherData {
        return weatherRepository.getWeather(location)
    }
}
