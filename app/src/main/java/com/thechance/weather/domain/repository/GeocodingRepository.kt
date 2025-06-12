package com.thechance.weather.domain.repository

import com.thechance.weather.domain.model.Location

interface GeocodingRepository {
    suspend fun getCityName(location: Location): String
}