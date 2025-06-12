package com.thechance.weather.data.remote.dto.address


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponseDto(
    @SerialName("address")
    val address: Address? = null,

    @SerialName("addresstype")
    val addressType: String,

    @SerialName("boundingbox")
    val boundingBox: List<String>,

    @SerialName("category")
    val category: String,

    @SerialName("display_name")
    val displayName: String,

    @SerialName("importance")
    val importance: Double,

    @SerialName("lat")
    val latitude: String? = null,

    @SerialName("licence")
    val licence: String,

    @SerialName("lon")
    val longitude: String? = null,

    @SerialName("name")
    val name: String,

    @SerialName("osm_id")
    val osmId: Int,

    @SerialName("osm_type")
    val osmType: String,

    @SerialName("place_id")
    val placeId: Int,

    @SerialName("place_rank")
    val placeRank: Int,

    @SerialName("type")
    val type: String
)