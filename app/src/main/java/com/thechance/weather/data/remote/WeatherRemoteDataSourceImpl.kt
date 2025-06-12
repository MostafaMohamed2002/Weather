package com.thechance.weather.data.remote

import com.thechance.weather.data.mapper.toDomain
import com.thechance.weather.data.remote.dto.weather.WeatherResponseDto
import com.thechance.weather.data.repository.weather.WeatherRemoteDataSource
import com.thechance.weather.data.utils.ApiUrl.OPEN_METEO_URL
import com.thechance.weather.data.utils.safeApiCall
import com.thechance.weather.domain.model.Location
import com.thechance.weather.domain.model.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient
) : WeatherRemoteDataSource {
    override suspend fun getWeatherData(location: Location): WeatherData {
        return safeApiCall("Weather API") {
            val weatherDto = client.get("${OPEN_METEO_URL}/forecast") {
                parameter("latitude", location.latitude)
                parameter("longitude", location.longitude)
                parameter("current", "temperature_2m,weather_code,wind_speed_10m,relative_humidity_2m,rain,uv_index,apparent_temperature,pressure_msl,is_day")
                parameter("hourly", "temperature_2m,weather_code,is_day")
                parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                parameter("timezone", "auto")
            }.body<WeatherResponseDto>()

            weatherDto.toDomain()
        }
    }
}