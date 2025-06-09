package com.thechance.weather.data.repository.location

import com.thechance.weather.domain.model.Location

interface LocationDataSource {
    suspend fun fetchCurrentLocation(): Location
}