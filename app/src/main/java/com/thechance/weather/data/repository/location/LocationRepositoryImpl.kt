package com.thechance.weather.data.repository.location

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
) : LocationRepository {
    override suspend fun getCurrentLocation(): Location {
        return locationDataSource.fetchCurrentLocation()


    }

}