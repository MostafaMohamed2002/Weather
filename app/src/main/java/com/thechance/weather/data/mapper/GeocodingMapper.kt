package com.thechance.weather.data.mapper

import com.thechance.weather.data.remote.dto.address.GeocodingResponseDto
import com.thechance.weather.domain.model.LocationAddress


fun GeocodingResponseDto.toDomain(): LocationAddress {
    return LocationAddress(
        city = this.address?.city ?: this.address?.town ?: this.address?.village ?: "Unknown Location",
        country = this.address?.country ?: ""
    )
}