package com.thechance.weather.domain.usecase

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.repository.GeocodingRepository

class GetCityNameUseCase(
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(location: Location): String {
        return geocodingRepository.getCityName(location)
    }
}