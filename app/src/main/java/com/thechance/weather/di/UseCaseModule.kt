package com.thechance.weather.di

import com.thechance.weather.domain.usecase.GetCityNameUseCase
import com.thechance.weather.domain.usecase.GetCurrentLocationUseCase
import com.thechance.weather.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCurrentLocationUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { GetCityNameUseCase(get()) }
}