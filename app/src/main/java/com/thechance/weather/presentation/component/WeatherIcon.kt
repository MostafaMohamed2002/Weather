package com.thechance.weather.presentation.component


import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.thechance.weather.R
import com.thechance.weather.domain.model.WeatherType
import java.util.Locale


@Composable
fun getWeatherIcon(weatherType: WeatherType, isDay: Boolean): Int {
    val context = LocalContext.current
    return remember(weatherType, isDay) {
        getDynamicIconResId(context, weatherType, isDay)
    }
}
@DrawableRes
private fun getDynamicIconResId(context: Context, weatherType: WeatherType, isDay: Boolean): Int {
    val baseIconName = weatherType.name.lowercase(Locale.ROOT)
    val timeSuffix = if (isDay) "_day" else "_night"

    val specificIconName = "${baseIconName}${timeSuffix}"
    var resourceId = context.resources.getIdentifier(
        specificIconName,
        "drawable",
        context.packageName
    )

    if (resourceId == 0) {
        resourceId = context.resources.getIdentifier(
            baseIconName,
            "drawable",
            context.packageName
        )
    }

    if (resourceId == 0) {
        resourceId = R.drawable.clearsky_day
    }

    return resourceId
}