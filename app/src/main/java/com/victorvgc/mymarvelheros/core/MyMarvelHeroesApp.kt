package com.victorvgc.mymarvelheros.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMarvelHeroesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyMarvelHeroesApp)
        }
    }
}