package com.thechance.weather.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.weather.domain.exception.GpsDisabledException
import com.thechance.weather.domain.exception.LocationNotFoundException
import com.thechance.weather.domain.exception.LocationPermissionException
import com.thechance.weather.domain.exception.NetworkException
import com.thechance.weather.domain.exception.ServerException
import com.thechance.weather.domain.usecase.GetWeatherForCurrentLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    // Its only dependency is the Use Case, keeping it clean and focused.
    private val getWeatherForCurrentLocationUseCase: GetWeatherForCurrentLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    /**
     * The main function called by the UI to trigger loading of all weather data.
     */
    fun loadWeather() {
        // Set the state to Loading immediately to show a progress indicator.
        _uiState.value = WeatherUiState.Loading

        // Launch a coroutine in the ViewModel's lifecycle scope.
        // This will be automatically cancelled if the ViewModel is cleared.
        viewModelScope.launch {
            // All the complex logic is now hidden inside the Use Case.
            val result = getWeatherForCurrentLocationUseCase()

            // Use .fold() to handle the success and failure cases from the Result.
            result.fold(
                onSuccess = { weatherDetails ->
                    // On success, update the state with the complete data package.
                    _uiState.value = WeatherUiState.Success(weatherDetails)
                },
                onFailure = { throwable ->
                    // On failure, map the technical exception to a user-friendly message
                    // and update the state with the error.
                    _uiState.value = WeatherUiState.Error(mapErrorToMessage(throwable))
                }
            )
        }
    }

    /**
     * This helper function converts a Throwable into a specific, user-friendly
     * error message by checking its type.
     */
    private fun mapErrorToMessage(error: Throwable): String {
        return when (error) {
            is LocationPermissionException -> "Please grant location permission to see the weather."
            is GpsDisabledException -> "Please enable GPS for weather updates."
            is LocationNotFoundException -> "Could not determine your current location."
            is NetworkException -> "Network error. Please check your internet connection and try again."
            is ServerException -> "Our servers are busy. Please try again later."
            else -> "An unexpected error occurred."
        }
    }
}
