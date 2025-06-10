package com.thechance.weather.data.remote

import com.thechance.weather.data.mapper.toDomain
import com.thechance.weather.data.remote.dto.address.GeocodingResponseDto
import com.thechance.weather.data.repository.geocoding.GeocodingRemoteDataSource
import com.thechance.weather.domain.exception.DataParsingException
import com.thechance.weather.domain.exception.NetworkException
import com.thechance.weather.domain.exception.ServerException
import com.thechance.weather.domain.exception.UnknownException
import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.LocationAddress
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.JsonConvertException
import java.io.IOException

class GeocodingRemoteDataSourceImpl(
    private val client: HttpClient
) : GeocodingRemoteDataSource {
    private companion object {
        const val BASE_URL = "https://nominatim.openstreetmap.org/reverse"
    }
    override suspend fun getLocationAddress(location: Location): LocationAddress {
        try {
            val geocodingDto = client.get(BASE_URL) {
                parameter("lat", location.latitude)
                parameter("lon", location.longitude)
                parameter("format", "jsonv2")
                parameter("zoom", 10)
                parameter("accept-language", "en")
            }.body<GeocodingResponseDto>()

            return geocodingDto.toDomain()

        }
        catch (e: IOException) {
            throw NetworkException("Could not connect to geocoding service.")
        } catch (e: ClientRequestException) { // 4xx errors
            throw ServerException("A client error occurred during geocoding.")
        } catch (e: ServerResponseException) { // 5xx errors
            throw ServerException("The geocoding server is currently unavailable.")
        } catch (e: JsonConvertException) {
            throw DataParsingException("Failed to parse geocoding server response.")
        } catch (e: Exception) {
            throw UnknownException("An unexpected geocoding error occurred.")
        }
    }
}