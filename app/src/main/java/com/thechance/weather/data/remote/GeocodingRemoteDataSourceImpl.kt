package com.thechance.weather.data.remote

import com.thechance.weather.data.remote.dto.address.GeocodingResponseDto
import com.thechance.weather.data.repository.geocoding.GeocodingRemoteDataSource
import com.thechance.weather.data.utils.ApiUrl.GEOCODING_URL
import com.thechance.weather.data.utils.safeApiCall
import com.thechance.weather.domain.model.Location
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GeocodingRemoteDataSourceImpl(
    private val client: HttpClient
) : GeocodingRemoteDataSource {

    override suspend fun getCityName(location: Location): String {
        return safeApiCall("Geocoding API") {
            val geocodingDto = client.get("${GEOCODING_URL}/reverse") {
                parameter("lat", location.latitude)
                parameter("lon", location.longitude)
                parameter("format", "jsonv2")
                parameter("zoom", 10)
                parameter("accept-language", "en")
            }.body<GeocodingResponseDto>()

            geocodingDto.address?.city ?: "Unknown Location"
        }
    }
}