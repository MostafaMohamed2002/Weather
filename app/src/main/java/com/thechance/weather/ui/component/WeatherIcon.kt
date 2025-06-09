package com.thechance.weather.ui.component


import androidx.compose.runtime.Composable
import com.thechance.weather.domain.model.WeatherType
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.thechance.weather.R
import java.util.Locale

/**
 * A safe composable function that dynamically determines the correct
 * weather icon resource ID based on the weather type and time of day.
 *
 * It uses 'remember' for optimal performance, avoiding recalculations on every recomposition.
 *
 * @param weatherType The weather condition enum from the domain model.
 * @param isDay True if it's daytime, false for nighttime.
 * @return The integer resource ID (@DrawableRes) of the correct icon.
 */
@Composable
fun getWeatherIcon(weatherType: WeatherType, isDay: Boolean): Int {
    val context = LocalContext.current

    // 'remember' is crucial here. It ensures we only run this logic when
    // the weatherType or isDay changes, not on every recomposition.
    return remember(weatherType, isDay) {
        getDynamicIconResId(context, weatherType, isDay)
    }
}

/**
 * The core logic that finds a drawable resource ID by dynamically constructing its name.
 *
 * This function first tries to find a specific day/night icon (e.g., "clearsky_day").
 * If it can't find one, it falls back to a generic icon (e.g., "fog") before
 * finally returning a default icon if nothing is found.
 *
 * @param context The Android context, needed to access resources.
 * @param weatherType The enum representing the weather condition.
 * @param isDay True for daytime, false for nighttime.
 * @return The resource ID of the found icon, or a default icon if none exist.
 */
@DrawableRes
private fun getDynamicIconResId(context: Context, weatherType: WeatherType, isDay: Boolean): Int {
    // 1. Convert the enum name to a lowercase string format (e.g., PartlyCloudy -> partlycloudy)
    val baseIconName = weatherType.name.lowercase(Locale.ROOT)
    val timeSuffix = if (isDay) "_day" else "_night"

    // 2. Try to find the specific day/night version of the icon (e.g., "partlycloudy_day")
    val specificIconName = "${baseIconName}${timeSuffix}"
    var resourceId = context.resources.getIdentifier(
        specificIconName,
        "drawable",
        context.packageName
    )

    // 3. If the specific day/night icon isn't found, try to find a generic one
    //    (e.g., if "fog_day" doesn't exist, it looks for just "fog").
    //    This is useful for icons like fog or rain that might be the same day or night.
    if (resourceId == 0) {
        resourceId = context.resources.getIdentifier(
            baseIconName,
            "drawable",
            context.packageName
        )
    }

    // 4. If still no icon is found, return a known default icon to prevent a crash.
    //    Make sure you have a `ic_default_weather.png` or similar in your drawables.
    if (resourceId == 0) {
        resourceId = R.drawable.clearsky_day // Provide a safe default icon
    }

    return resourceId
}