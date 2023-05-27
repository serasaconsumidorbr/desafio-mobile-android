package com.victorvgc.mymarvelheros.core

import android.app.Application
import com.victorvgc.mymarvelheros.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMarvelHeroesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyMarvelHeroesApp)

            modules(homeModule)
        }
    }
}