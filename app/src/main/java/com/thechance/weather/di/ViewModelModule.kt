package com.thechance.weather.di

import com.thechance.weather.ui.screen.home.WeatherViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::WeatherViewModel)
}
