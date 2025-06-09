package com.thechance.weather.data.repository.geocoding

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.LocationAddress
import com.thechance.weather.domain.repository.GeocodingRepository

class GeocodingRepositoryImpl(
    private val remoteDataSource: GeocodingRemoteDataSource
) : GeocodingRepository {
    override suspend fun getLocationAddress(location: Location): Result<LocationAddress> {
        return try {
            val locationAddress = remoteDataSource.getLocationAddress(location)
            Result.success(locationAddress)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}