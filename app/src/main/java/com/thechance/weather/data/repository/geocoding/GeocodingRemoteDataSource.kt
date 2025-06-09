package com.thechance.weather.data.repository.geocoding

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.LocationAddress

interface GeocodingRemoteDataSource {
    suspend fun getLocationAddress(location: Location): LocationAddress
}