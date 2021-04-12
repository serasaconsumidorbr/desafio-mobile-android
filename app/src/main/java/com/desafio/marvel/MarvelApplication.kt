package com.desafio.marvel

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    private lateinit var mInstance: MarvelApplication

    override fun onCreate() {
        super.onCreate()

        mInstance = this

        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)

        }
    }

}