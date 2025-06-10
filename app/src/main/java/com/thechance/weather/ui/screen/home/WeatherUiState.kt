package com.thechance.weather.ui.screen.home

import com.thechance.weather.domain.model.WeatherDetails

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val data: WeatherDetails) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}