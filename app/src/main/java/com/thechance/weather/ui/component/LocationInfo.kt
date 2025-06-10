package com.thechance.weather.ui.component

import WeatherTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R

@Composable
fun LocationInfo(
    modifier: Modifier = Modifier,
    location: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(com.thechance.weather.R.drawable.location_05),
            contentDescription = "Location",
            tint = WeatherTheme.colors.locationTextColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.size(4.dp))
        Text(
            text = location,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                fontWeight = FontWeight(500),
                color = WeatherTheme.colors.locationTextColor,
                letterSpacing = 0.25.sp,
            )
        )
    }
}

@Preview
@Composable
fun LocationInfoPreview() {
    LocationInfo(
        location = "Baghdad"
    )
}