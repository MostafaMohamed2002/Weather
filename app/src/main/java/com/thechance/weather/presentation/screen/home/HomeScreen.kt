package com.thechance.weather.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.weather.presentation.component.HomeScreenDailySection
import com.thechance.weather.presentation.component.HomeScreenDetailsSection
import com.thechance.weather.presentation.component.HomeScreenError
import com.thechance.weather.presentation.component.HomeScreenHeaderSection
import com.thechance.weather.presentation.component.HomeScreenHourlySection
import com.thechance.weather.presentation.model.WeatherUiState
import com.thechance.weather.presentation.theme.WeatherAppTheme
import com.thechance.weather.presentation.theme.WeatherTheme
import org.koin.compose.koinInject


@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()
    // A simple boolean state to switch between expanded and collapsed headers.
    val isCollapsed by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 || lazyListState.firstVisibleItemScrollOffset > 50
        }
    }

    val isDay = uiState.headerState?.isDay ?: false
    WeatherAppTheme(useDarkTheme = !isDay) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(WeatherTheme.colors.backgroundBrush),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(
                    color = WeatherTheme.colors.primaryColor
                )

                uiState.error != null -> HomeScreenError(
                    message = uiState.error!!,
                    onRetry = { viewModel.loadWeather() }
                )

                uiState.headerState != null -> {
                    HomeScreenContent(
                        state = uiState,
                        lazyListState = lazyListState,
                        isCollapsed = isCollapsed
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    state: WeatherUiState,
    lazyListState: LazyListState,
    isCollapsed: Boolean
) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(bottom = 40.dp)
    ) {
        item {
            HomeScreenHeaderSection(
                isCollapsed = isCollapsed,
                headerState = state.headerState!!
            )
        }

        item {
            HomeScreenDetailsSection(state.detailsState)
        }

        item {
            HomeScreenHourlySection(state.hourlyForecastState)
        }

        item {
            HomeScreenDailySection(state.dailyForecastState)
        }
    }
}



