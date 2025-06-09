package com.thechance.weather.domain.repository

import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.LocationAddress

interface GeocodingRepository {
    suspend fun getLocationAddress(location: Location): Result<LocationAddress>
}