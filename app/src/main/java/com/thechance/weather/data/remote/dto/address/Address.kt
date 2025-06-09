package com.thechance.weather.data.remote.dto.address


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("city")
    val city: String? = null, // city might not always be present
    @SerialName("town")
    val town: String? = null, // It might be a town instead of a city
    @SerialName("village")
    val village: String? = null, // Or even a village
    @SerialName("country")
    val country: String? = null,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("ISO3166-2-lvl4")
    val iSO31662Lvl4: String,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("state")
    val state: String? = null
)