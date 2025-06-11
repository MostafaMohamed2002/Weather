package com.thechance.weather.ui.screen.home

import AnimationCollapsingScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R
import com.thechance.weather.domain.model.WeatherDetails
import com.thechance.weather.ui.component.DailyForecastItem
import com.thechance.weather.ui.component.HourlyInfoItem
import com.thechance.weather.ui.component.LocationInfo
import com.thechance.weather.ui.component.WeatherAppTheme
import com.thechance.weather.ui.component.WeatherDetailsGrid
import com.thechance.weather.ui.component.WeatherTheme
import com.thechance.weather.ui.component.getWeatherIcon
import org.koin.compose.koinInject
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }
    val isDay =
        (uiState as? WeatherUiState.Success)?.data?.weatherData?.currentWeather?.isDay ?: false
    WeatherAppTheme(useDarkTheme = !isDay) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(WeatherTheme.colors.backgroundBrush),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is WeatherUiState.Loading -> LoadingScreen()
                is WeatherUiState.Success -> WeatherSuccessScreen(
                    weatherDetails = state.data,
                )

                is WeatherUiState.Error -> ErrorScreen(
                    message = state.message,
                    onRetry = { viewModel.loadWeather() }
                )
            }
        }
    }

}

@Composable
fun WeatherSuccessScreen(weatherDetails: WeatherDetails) {
    val lazyListState = rememberLazyListState()
    val collapseThreshold = 10 // in pixels, tweak this value to control when collapse starts
    val isCollapsed by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 ||
                    lazyListState.firstVisibleItemScrollOffset >= collapseThreshold
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState
    ) {
        item {
            LocationInfo(
                location = weatherDetails.locationAddress.city,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 24.dp)
            )
        }
        item {
            AnimationCollapsingScreen(
                isCollapsed = isCollapsed,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
                temperature = weatherDetails.weatherData.currentWeather.temperature.roundToInt()
                    .toString(),
                temperatureDescription = weatherDetails.weatherData.currentWeather.weatherType.description,
                minTemperature = weatherDetails.weatherData.currentWeather.dailyMinTemp.roundToInt()
                    .toString(),
                maxTemperature = weatherDetails.weatherData.currentWeather.dailyMaxTemp.roundToInt()
                    .toString(),
                image = painterResource(
                    getWeatherIcon(
                        weatherDetails.weatherData.currentWeather.weatherType,
                        isDay = weatherDetails.weatherData.currentWeather.isDay
                    )
                ),
            )
        }

        item {
            WeatherDetailsGrid(
                currentWeather = weatherDetails.weatherData.currentWeather,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 24.dp)
            )
        }
        item {
            Text(
                text = "Today",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(600),
                    color = WeatherTheme.colors.textColor,
                    letterSpacing = 0.25.sp,
                ), modifier = Modifier.padding(top = 24.dp, start = 12.dp)
            )
        }
        item {
            val today = LocalDate.now()
            val todaysHourlyForecast = remember(weatherDetails.weatherData.hourlyForecast) {
                weatherDetails.weatherData.hourlyForecast.filter {
                    it.time.toLocalDate() == today
                }
            }
            LazyRow(
                modifier = Modifier.padding(top = 12.dp),
                contentPadding = PaddingValues(start = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = todaysHourlyForecast.size,
                    key = { index ->
                        todaysHourlyForecast[index].time
                    },
                ) { index ->
                    // Get the item from the filtered list
                    val hourlyItem = todaysHourlyForecast[index]

                    HourlyInfoItem(
                        image = painterResource(
                            getWeatherIcon(
                                weatherType = hourlyItem.weatherType,
                                isDay = hourlyItem.isDay
                            )
                        ),
                        temperature = hourlyItem.temperature.roundToInt().toString(),
                        time = hourlyItem.time.toLocalTime().toString(),
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
        item {
            Text(
                text = "Next 7 days",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(600),
                    color = WeatherTheme.colors.textColor,
                    letterSpacing = 0.25.sp,
                ),
                modifier = Modifier.padding(start = 12.dp, top = 24.dp)
            )
        }
        item {
            Box(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = WeatherTheme.colors.containerColor,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = WeatherTheme.colors.borderColor,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(top = 4.dp, bottom = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    weatherDetails.weatherData.dailyForecast.forEachIndexed { index, forecast ->
                        DailyForecastItem(
                            dailyForecast = forecast,
                            modifier = Modifier,
                            isDay = weatherDetails.weatherData.currentWeather.isDay
                        )
                        // Add a divider between items, but not after the last one
                        if (index < weatherDetails.weatherData.dailyForecast.size - 1) {
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = WeatherTheme.colors.borderColor
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(Modifier.size(40.dp))
        }

    }
}

@Composable
fun LoadingScreen() {
    CircularProgressIndicator(
        color = WeatherTheme.colors.textColor
    )
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable._81599_error_icon), // Make sure you have an error icon
            contentDescription = null,
            tint = WeatherTheme.colors.textColor,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Something went wrong",
            fontSize = 20.sp,
            color = WeatherTheme.colors.textColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            color = WeatherTheme.colors.textColor87,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = WeatherTheme.colors.containerColor
            )
        ) {
            Text("Try Again", color = WeatherTheme.colors.textColor)
        }
    }
}

