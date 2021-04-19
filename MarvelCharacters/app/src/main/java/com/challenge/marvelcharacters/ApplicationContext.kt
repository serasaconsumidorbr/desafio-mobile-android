package com.challenge.marvelcharacters

import android.app.Application
import com.challenge.marvelcharacters.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class AplicationContext : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@AplicationContext)
            modules(appModule)
        }
    }
}