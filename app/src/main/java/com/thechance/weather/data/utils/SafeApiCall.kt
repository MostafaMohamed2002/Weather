package com.thechance.weather.data.utils

import com.thechance.weather.domain.exception.DataParsingException
import com.thechance.weather.domain.exception.NetworkException
import com.thechance.weather.domain.exception.ServerException
import com.thechance.weather.domain.exception.UnknownException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException
import java.io.IOException

suspend fun <T> safeApiCall(serviceName: String, apiCall: suspend () -> T): T {
    return try {
        apiCall()
    } catch (e: IOException) {
        throw NetworkException(
            message = "Network error in $serviceName. Please check your connection.",
            exception = e
        )
    } catch (e: ClientRequestException) {
        throw ServerException(
            message = "A client error occurred in $serviceName.",
            exception = e
        )
    } catch (e: ServerResponseException) {
        throw ServerException(
            message = "The $serviceName server is currently unavailable.",
            exception = e
        )
    } catch (e: JsonConvertException) {
        throw DataParsingException(
            message = "Failed to parse the response from the $serviceName.",
            exception = e
        )
    } catch (e: Exception) {
        throw UnknownException(
            message = "An unexpected error occurred in the $serviceName.",
            exception = e
        )
    }
}
