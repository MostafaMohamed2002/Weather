package com.thechance.weather.domain.exception

/**
 * A sealed class to represent all custom exceptions for the application.
 * It now includes an optional 'exception' to preserve the original exception for logging.
 */
sealed class WeatherAppException(
    override val message: String,
    val exception: Throwable? = null
) : Exception(message, exception)


class NetworkException(
    message: String = "A network error occurred.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class ServerException(
    message: String = "A server error occurred.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class DataParsingException(
    message: String = "A data parsing error occurred.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class LocationPermissionException(
    message: String = "Location permission is required but was denied.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class GpsDisabledException(
    message: String = "GPS is not enabled.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class LocationNotFoundException(
    message: String = "Could not retrieve a location.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)

class UnknownException(
    message: String = "An unexpected error occurred.",
    exception: Throwable? = null
) : WeatherAppException(message, exception)
