package com.thechance.weather.data.remote.dto.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("apparent_temperature")
    val apparentTemperature: Double,

    @SerialName("interval")
    val interval: Int,

    @SerialName("is_day")
    val isDay: Int? = null,

    @SerialName("pressure_msl")
    val pressureMsl: Double,

    @SerialName("rain")
    val rain: Double,

    @SerialName("relative_humidity_2m")
    val relativeHumidity: Int,

    @SerialName("temperature_2m")
    val temperature: Double,

    @SerialName("time")
    val time: String,

    @SerialName("uv_index")
    val uvIndex: Double,

    @SerialName("weather_code")
    val weatherCode: Int,

    @SerialName("wind_speed_10m")
    val windSpeed: Double
)
