package com.thechance.weather.data.repository.location

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
) : LocationRepository {
    override suspend fun getCurrentLocation(): Result<Location> {
        return try {
            val location = locationDataSource.fetchCurrentLocation()
            Result.success(location)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}