package com.thechance.weather

import android.app.Application
import com.thechance.weather.di.appModule
import com.thechance.weather.di.dataSourceModule
import com.thechance.weather.di.repositoryModule
import com.thechance.weather.di.useCaseModule
import com.thechance.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                appModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}