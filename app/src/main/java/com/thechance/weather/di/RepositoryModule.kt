package com.thechance.weather.di

import com.thechance.weather.data.repository.geocoding.GeocodingRepositoryImpl
import com.thechance.weather.data.repository.location.LocationRepositoryImpl
import com.thechance.weather.data.repository.weather.WeatherRepositoryImpl
import com.thechance.weather.domain.repository.GeocodingRepository
import com.thechance.weather.domain.repository.LocationRepository
import com.thechance.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<GeocodingRepository> { GeocodingRepositoryImpl(get()) }
}
