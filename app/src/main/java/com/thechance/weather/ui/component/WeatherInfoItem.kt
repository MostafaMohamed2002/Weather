package com.thechance.weather.ui.component

import WeatherAppTheme
import WeatherTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.weather.R

@Composable
fun WeatherInfoItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    value: String,
) {
    Box(
        modifier
            .width(108.dp)
            .wrapContentHeight()
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
                contentDescription = null,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = title,
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
                text = value,
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

@Preview
@Composable
fun WeatherInfoItemPreview() {
    WeatherAppTheme(
        useDarkTheme = true,
        content = {
            WeatherInfoItem(
                painter = painterResource(id = R.drawable.fast_wind),
                title = "13 KM/h",
                value = "Wind"
            )
        }
    )

}

