package com.thechance.weather.data.remote.dto.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerialName("time")
    val time: List<String>,

    @SerialName("temperature_2m")
    val temperatures: List<Double>,

    @SerialName("weather_code")
    val weatherCodes: List<Int>,

    @SerialName("is_day")
    val isDay: List<Int>
)