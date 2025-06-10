package com.thechance.weather.ui.component

import WeatherAppTheme
import WeatherTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
fun HourlyInfoItem(
    modifier: Modifier = Modifier,
    image: Painter,
    temperature: String,
    time: String
) {
    Box(
         modifier = modifier
             .height(132.dp)
             .width(88.dp)

    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = WeatherTheme.colors.borderColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .height(120.dp)
                .background(WeatherTheme.colors.containerColor)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(58.dp)
                    .width(64.dp)
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = "${temperature}°C",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
                    color = WeatherTheme.colors.textColor87,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = time,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist)),
                    fontWeight = FontWeight(500),
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
fun HourlyInfoItemPreview() {
    WeatherAppTheme(
        useDarkTheme = false,
        content = {
            HourlyInfoItem(
                image = painterResource(id = R.drawable.partlycloudy_night),
                temperature = "25°C",
                time = "12:00"
            )
        }
    )

}
