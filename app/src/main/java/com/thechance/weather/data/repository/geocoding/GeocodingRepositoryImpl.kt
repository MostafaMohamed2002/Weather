package com.thechance.weather.data.repository.geocoding

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.repository.GeocodingRepository

class GeocodingRepositoryImpl(
    private val remoteDataSource: GeocodingRemoteDataSource
) : GeocodingRepository {
    override suspend fun getCityName(location: Location): String {

        return remoteDataSource.getCityName(location)

    }
}