package com.thechance.weather.domain.model


enum class WeatherType(val description: String, val wmoCode: Int) {
    ClearSky("Clear sky", 0),
    MainlyClear("Mainly clear", 1),
    PartlyCloudy("Partly cloudy", 2),
    Overcast("Overcast", 3),
    Fog("Fog", 45),
    DepositingRimeFog("Depositing rime fog", 48),
    LightDrizzle("Light drizzle", 51),
    ModerateDrizzle("Moderate drizzle", 53),
    DenseDrizzle("Dense drizzle", 55),
    LightFreezingDrizzle("Light freezing drizzle", 56),
    DenseFreezingDrizzle("Dense freezing drizzle", 57),
    SlightRain("Slight rain", 61),
    ModerateRain("Moderate rain", 63),
    HeavyRain("Heavy rain", 65),
    LightFreezingRain("Light freezing rain", 66),
    HeavyFreezingRain("Heavy freezing rain", 67),
    SlightSnowFall("Slight snow fall", 71),
    ModerateSnowFall("Moderate snow fall", 73),
    HeavySnowFall("Heavy snow fall", 75),
    SnowGrains("Snow grains", 77),
    SlightRainShowers("Slight rain showers", 80),
    ModerateRainShowers("Moderate rain showers", 81),
    ViolentRainShowers("Violent rain showers", 82),
    SlightSnowShowers("Slight snow showers", 85),
    HeavySnowShowers("Heavy snow showers", 86),
    ModerateThunderstorm("Thunderstorm", 95),
    ThunderstormWithSlightHail("Thunderstorm with slight hail", 96),
    ThunderstormWithHeavyHail("Thunderstorm with heavy hail", 99);

    // Companion object to provide a helper function for easy conversion.
    companion object {
        fun fromWmoCode(code: Int): WeatherType {
            return values().find { it.wmoCode == code }
                ?: ClearSky // Default to ClearSky if code is unknown
        }
    }
}