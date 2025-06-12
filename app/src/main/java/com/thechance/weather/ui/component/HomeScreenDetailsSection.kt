package com.thechance.weather.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.weather.ui.model.DetailItemState

@Composable
fun HomeScreenDetailsSection(details: List<DetailItemState>) {
    WeatherDetailsGrid(
        detailsState = details,
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 24.dp)
    )
}
