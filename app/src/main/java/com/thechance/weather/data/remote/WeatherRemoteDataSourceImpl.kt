package com.thechance.weather.data.remote

import com.thechance.weather.data.mapper.toDomain
import com.thechance.weather.data.remote.dto.weather.WeatherResponseDto
import com.thechance.weather.data.repository.weather.WeatherRemoteDataSource
import com.thechance.weather.domain.exception.DataParsingException
import com.thechance.weather.domain.exception.NetworkException
import com.thechance.weather.domain.exception.ServerException
import com.thechance.weather.domain.exception.UnknownException
import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.JsonConvertException
import java.io.IOException

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient
) : WeatherRemoteDataSource {
    override suspend fun getWeatherData(location: Location): WeatherData {
        try {
            val weatherDto = client.get("https://api.open-meteo.com/v1/forecast") {
                parameter("latitude", location.latitude)
                parameter("longitude", location.longitude)
                parameter("current", "temperature_2m,weather_code,wind_speed_10m,relative_humidity_2m,rain,uv_index,apparent_temperature,pressure_msl,is_day")
                parameter("hourly", "temperature_2m,weather_code,is_day")
                parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                parameter("timezone", "auto")
            }.body<WeatherResponseDto>()

            return weatherDto.toDomain()

        }
        // Catch specific exceptions and throw your new custom ones
        catch (e: IOException) {
            throw NetworkException()
        } catch (e: ClientRequestException) { // 4xx errors
            throw ServerException("A client error occurred: ${e.response.status.description}")
        } catch (e: ServerResponseException) { // 5xx errors
            throw ServerException("A server error occurred: ${e.response.status.description}")
        } catch (e: JsonConvertException) {
            throw DataParsingException("Failed to parse server response.")
        } catch (e: Exception) {
            // A final catch-all for any other unexpected errors
            throw UnknownException("An unexpected error occurred during weather fetch.")
        }
    }
}