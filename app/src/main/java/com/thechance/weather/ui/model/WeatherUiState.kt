package com.thechance.weather.ui.model

data class WeatherUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val headerState: HeaderState? = null,
    val detailsState: List<DetailItemState> = emptyList(),
    val hourlyForecastState: List<HourlyForecastItemState> = emptyList(),
    val dailyForecastState: List<DailyForecastItemState> = emptyList()
)