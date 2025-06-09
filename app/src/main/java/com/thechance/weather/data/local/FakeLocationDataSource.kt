package com.thechance.weather.data.local

import com.thechance.weather.data.repository.location.LocationDataSource
import com.thechance.weather.domain.model.Location

class FakeLocationDataSource : LocationDataSource {
    override suspend fun fetchCurrentLocation(): Location {
        return Location(latitude = 30.150, longitude = 31.633)
    }
}