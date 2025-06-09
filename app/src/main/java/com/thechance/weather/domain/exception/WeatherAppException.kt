package com.thechance.weather.domain.exception

/**
 * A sealed class to represent all custom exceptions related to the weather feature.
 */
sealed class WeatherAppException(message: String) : Exception(message)

class NetworkException(
    message: String = "A network error occurred."
) : WeatherAppException(message)

class ServerException(
    message: String = "A server error occurred."
) : WeatherAppException(message)

class DataParsingException(
    message: String = "A data parsing error occurred."
) : WeatherAppException(message)

class LocationPermissionAppException(
    message: String = "Location permission is required but was denied."
) : WeatherAppException(message)

class GpsDisabledAppException(
    message: String = "GPS is not enabled."
) : WeatherAppException(message)

class LocationNotFoundException(
    message: String = "Could not retrieve a location."
) : WeatherAppException(message)

class UnknownDataAppException(
    message: String = "An unexpected data error occurred."
) : WeatherAppException(message)