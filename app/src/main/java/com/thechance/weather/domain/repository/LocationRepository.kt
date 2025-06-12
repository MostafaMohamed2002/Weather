package com.thechance.weather.domain.repository

import com.thechance.weather.domain.model.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Location
}