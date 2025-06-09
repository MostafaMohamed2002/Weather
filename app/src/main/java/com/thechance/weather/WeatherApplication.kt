package com.thechance.weather

import android.app.Application
import com.thechance.weather.di.dataModule
import com.thechance.weather.di.domainModule
import com.thechance.weather.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin activity
            androidLogger()
            // Provide Android context
            androidContext(this@WeatherApplication)
            // Declare modules to use
            modules(dataModule, domainModule, presentationModule)
        }
    }
}