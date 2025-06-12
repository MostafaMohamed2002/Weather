package com.thechance.weather.data.remote.dto.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    @SerialName("time")
    val time: List<String>,

    @SerialName("weather_code")
    val weatherCodes: List<Int>,

    @SerialName("temperature_2m_max")
    val maxTemperatures: List<Double>,

    @SerialName("temperature_2m_min")
    val minTemperatures: List<Double>
)
