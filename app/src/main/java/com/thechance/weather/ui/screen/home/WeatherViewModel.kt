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
    private val getWeatherForCurrentLocationUseCase: GetWeatherForCurrentLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeather() {
        _uiState.value = WeatherUiState.Loading

        viewModelScope.launch {
            val result = getWeatherForCurrentLocationUseCase()
            result.fold(
                onSuccess = { weatherDetails ->
                    _uiState.value = WeatherUiState.Success(weatherDetails)
                },
                onFailure = { throwable ->
                    _uiState.value = WeatherUiState.Error(mapErrorToMessage(throwable))
                }
            )
        }
    }

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
