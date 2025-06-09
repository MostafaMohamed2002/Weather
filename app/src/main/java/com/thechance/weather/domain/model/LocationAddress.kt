package com.thechance.weather.domain.model

/**
 * Represents a human-readable address.
 * This is the OUTPUT of the geocoding repository.
 */
data class LocationAddress(
    val city: String,
    val country: String
)