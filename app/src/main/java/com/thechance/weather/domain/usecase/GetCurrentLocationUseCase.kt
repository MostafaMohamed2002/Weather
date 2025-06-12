package com.thechance.weather.domain.usecase

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Location {
        return locationRepository.getCurrentLocation()
    }
}
