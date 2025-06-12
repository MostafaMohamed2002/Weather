package com.thechance.weather.di

import com.thechance.weather.data.local.FusedLocationDataSource
import com.thechance.weather.data.remote.GeocodingRemoteDataSourceImpl
import com.thechance.weather.data.remote.WeatherRemoteDataSourceImpl
import com.thechance.weather.data.repository.geocoding.GeocodingRemoteDataSource
import com.thechance.weather.data.repository.location.LocationDataSource
import com.thechance.weather.data.repository.weather.WeatherRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<LocationDataSource> { FusedLocationDataSource(androidContext()) }

    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single<GeocodingRemoteDataSource> { GeocodingRemoteDataSourceImpl(get()) }
}
