package com.thechance.weather.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R
import com.thechance.weather.ui.model.DetailItemState
import com.thechance.weather.ui.theme.WeatherTheme


@Composable
fun WeatherInfoItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    formattedValue: String,
    label: String,
) {
    Box(
        modifier
            .width(108.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(WeatherTheme.colors.containerColor)
            .border(
                width = 1.dp,
                color = WeatherTheme.colors.borderColor,
                shape = RoundedCornerShape(size = 24.dp)
            ), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = label,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(24.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = formattedValue,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
                    color = WeatherTheme.colors.textColor87,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(400),
                    color = WeatherTheme.colors.textColor60,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
        }
    }
}



@Composable
fun WeatherDetailsGrid(
    detailsState: List<DetailItemState>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            detailsState.take(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.value,
                    label = detail.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            detailsState.drop(3).forEach { detail ->
                WeatherInfoItem(
                    painter = painterResource(id = detail.iconRes),
                    formattedValue = detail.value,
                    label = detail.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

