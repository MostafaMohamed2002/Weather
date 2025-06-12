package com.thechance.weather.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.weather.domain.exception.DataParsingException
import com.thechance.weather.domain.exception.GpsDisabledException
import com.thechance.weather.domain.exception.LocationNotFoundException
import com.thechance.weather.domain.exception.LocationPermissionException
import com.thechance.weather.domain.exception.NetworkException
import com.thechance.weather.domain.exception.ServerException
import com.thechance.weather.domain.usecase.GetCityNameUseCase
import com.thechance.weather.domain.usecase.GetCurrentLocationUseCase
import com.thechance.weather.domain.usecase.GetWeatherUseCase
import com.thechance.weather.presentation.mapper.toUiState
import com.thechance.weather.presentation.model.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCityNameUseCase: GetCityNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState

    init {
        loadWeather()
    }

    fun loadWeather() {
        _uiState.value = WeatherUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val location = getCurrentLocationUseCase()
                val weatherData = getWeatherUseCase(location)
                val cityName = getCityNameUseCase(location)
                _uiState.value = weatherData.toUiState(cityName)

            } catch (error: Exception) {
                _uiState.value = WeatherUiState(isLoading = false, error = mapErrorToMessage(error))
                Log.e("WeatherViewModel", "Error loading weather: ${error.message}")
                Log.e("WeatherViewModel", "Error loading weather: ${error.printStackTrace()}")
            }
        }
    }

    private fun mapErrorToMessage(error: Throwable): String {
        return when (error) {
            is LocationPermissionException -> "Please grant location permission to see the weather."
            is GpsDisabledException -> "Please enable GPS for weather updates."
            is LocationNotFoundException -> "Could not determine your current location."
            is NetworkException -> "Network error. Please check your internet connection and try again."
            is ServerException -> "Our servers are busy. Please try again later."
            is DataParsingException -> "An error occurred while reading weather data."
            else -> error.message ?: "An unexpected error occurred."
        }
    }
}
