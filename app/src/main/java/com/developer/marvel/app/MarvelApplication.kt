package com.developer.marvel.app

import android.app.Application
import com.developer.marvel.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MarvelApplication)

            modules(
                listOf(
                    appModule,
                    usecasesModule,
                    repositoryModule,
                    datasourceModule,
                )
            )
        }
    }
}