# Weather (Android)

A small Android app that displays current weather, hourly and daily forecasts for the user's current location. Built with Jetpack Compose and following a Clean Architecture + MVVM structure.

## Screenshots & Demo placeholders
### light mode
https://github.com/user-attachments/assets/0252b39c-ea56-4ec1-bf2a-9116c0f54171
### dark mode 
https://github.com/user-attachments/assets/2eb7bdac-0092-42ea-b37c-f254d1ec242c

## Highlights

- Single-screen Compose UI showing:
  - Current conditions (temperature, weather description, icon)
  - Hourly forecast
  - Daily forecast
  - Details cards (humidity, pressure, wind, UV index, etc.)
- Clean architecture (domain, data, presentation layers)
- MVVM with a Compose-friendly ViewModel
- Koin for dependency injection
- Ktor HTTP client + kotlinx.serialization for networking
- Uses Google Play Services Fused Location to obtain the device location
- Uses Open-Meteo (https://open-meteo.com) for weather data and OpenStreetMap Nominatim for reverse geocoding (no API key required)

## Project structure

- `app/src/main/java/com/thechance.weather`
  - `data` — remote data sources (Ktor clients), local (fused) location provider, mappers
  - `domain` — models, repository interfaces, use cases, domain exceptions
  - `presentation` — Compose UI, ViewModels, UI models and themes
  - `di` — Koin modules wiring the app together
  - `WeatherApplication` — Koin initialization

## Key libraries & versions (from project)

- Android Gradle Plugin: 8.10.1
- Kotlin: 2.0.21
- Jetpack Compose (BOM): 2024.09.00
- Ktor client: 3.1.3
- Koin: 4.0.4
- Google Play Services Location
- kotlinx.serialization / kotlinx.coroutines

(See `gradle/libs.versions.toml` and `app/build.gradle.kts` for full details.)

## Permissions

The app requires the following runtime permissions:

- `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION` — to fetch device location via FusedLocationProvider
- `INTERNET` — to call the remote APIs

The `AndroidManifest.xml` already declares these permissions. The app's UI / ViewModel maps permission and location errors to friendly messages.

## External APIs

- Weather: Open-Meteo (https://api.open-meteo.com/v1) — used by `WeatherRemoteDataSourceImpl`. Open-Meteo is free and does not require an API key.
- Reverse Geocoding: Nominatim (https://nominatim.openstreetmap.org) — used by `GeocodingRemoteDataSourceImpl` to obtain a city name. No API key needed, but please respect usage policy for heavy traffic.

No API keys or secret configuration are required to run the app.

## How it works (brief)

- On start, `WeatherViewModel` triggers `loadWeather()`.
- `GetCurrentLocationUseCase` retrieves the location from `LocationRepository` which uses Google Play Services (FusedLocationDataSource).
- `GetWeatherUseCase` calls the `WeatherRepository` which uses a Ktor client to fetch weather data from Open-Meteo.
- `GetCityNameUseCase` calls Nominatim to resolve a readable city name for display.
- The domain objects are mapped to UI state models via `presentation.mapper` and rendered using Jetpack Compose.

## Running locally

Requirements:

- Android Studio Flamingo or newer (recommended)
- Android SDK configured for API level 36 (compileSdk 36)
- Device or emulator with Google Play services for Fused Location

Steps:

1. Open the project in Android Studio.
2. Let Gradle sync and download dependencies.
3. Build and run on an emulator or device (API 26+).

Note: The app uses Google Play Services for location. Use an emulator image that includes Google Play, or test on a real device.

## Error handling

Common errors are mapped to user-facing messages in `WeatherViewModel.mapErrorToMessage()`:
- Location permission issues
- GPS disabled
- Network / server errors
- Data parsing issues

