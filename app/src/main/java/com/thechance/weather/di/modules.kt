package com.thechance.weather.di

import com.thechance.weather.data.local.FakeLocationDataSource
import com.thechance.weather.data.local.FusedLocationDataSource
import com.thechance.weather.data.remote.GeocodingRemoteDataSourceImpl
import com.thechance.weather.data.remote.WeatherRemoteDataSourceImpl
import com.thechance.weather.data.repository.geocoding.GeocodingRemoteDataSource
import com.thechance.weather.data.repository.geocoding.GeocodingRepositoryImpl
import com.thechance.weather.data.repository.location.LocationDataSource
import com.thechance.weather.data.repository.location.LocationRepositoryImpl
import com.thechance.weather.data.repository.weather.WeatherRemoteDataSource
import com.thechance.weather.data.repository.weather.WeatherRepositoryImpl
import com.thechance.weather.domain.repository.GeocodingRepository
import com.thechance.weather.domain.repository.LocationRepository
import com.thechance.weather.domain.repository.WeatherRepository
import com.thechance.weather.domain.usecase.GetWeatherForCurrentLocationUseCase
import com.thechance.weather.ui.screen.home.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val domainModule = module {
    factory { GetWeatherForCurrentLocationUseCase(get(), get(), get()) }
}
val presentationModule = module {
    viewModel { WeatherViewModel(get()) }
}
val dataModule = module {

    // --- A SINGLE, SHARED HTTP CLIENT CONFIGURATION ---
    single {
        HttpClient(Android) {
            install(Logging) { level = LogLevel.BODY }
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
    }

    // --- LOCATION ---
    single<LocationDataSource>(named(LocationType.REAL)) {
        FusedLocationDataSource(androidContext())
    }
    single<LocationDataSource>(named(LocationType.FAKE)) {
        FakeLocationDataSource()
    }
    single<LocationRepository> {
        LocationRepositoryImpl(get(named(LocationType.REAL)))
    }

    // --- GEOCODING ---
    single<GeocodingRemoteDataSource> {
        GeocodingRemoteDataSourceImpl(get())
    }
    single<GeocodingRepository> {
        GeocodingRepositoryImpl(get())
    }

    // --- WEATHER ---
    single<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl(get())
    }
    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}

