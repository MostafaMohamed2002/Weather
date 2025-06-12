package com.thechance.weather.data.remote.dto.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("temperature_2m_max")
    val maxTemperature: String,
    @SerialName("temperature_2m_min")
    val minTemperature: String
)
