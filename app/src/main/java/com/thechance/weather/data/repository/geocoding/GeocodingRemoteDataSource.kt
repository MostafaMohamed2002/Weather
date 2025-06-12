package com.thechance.weather.data.repository.geocoding

import com.thechance.weather.domain.model.Location

interface GeocodingRemoteDataSource {
    suspend fun getCityName(location: Location): String
}