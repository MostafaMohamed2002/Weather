package com.thechance.weather.data.remote.dto.address


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Address(
    @SerialName("city")
    val city: String? = null,

    @SerialName("town")
    val town: String? = null,

    @SerialName("village")
    val village: String? = null,

    @SerialName("country")
    val country: String? = null,

    @SerialName("country_code")
    val countryCode: String,

    @SerialName("ISO3166-2-lvl4")
    val iso31662Lvl4: String,

    @SerialName("postcode")
    val postcode: String? = null,

    @SerialName("state")
    val state: String? = null
)
