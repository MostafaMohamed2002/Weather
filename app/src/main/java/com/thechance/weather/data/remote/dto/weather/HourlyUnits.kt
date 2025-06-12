package com.thechance.weather.data.remote.dto.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(
    @SerialName("time")
    val time: String,

    @SerialName("temperature_2m")
    val temperature: String,

    @SerialName("weather_code")
    val weatherCode: String,

    @SerialName("is_day")
    val isDay: String
)
