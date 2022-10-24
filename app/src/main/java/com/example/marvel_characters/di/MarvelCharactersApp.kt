package com.example.marvel_characters.di

import android.app.Application
import com.example.marvel_characters.di.Module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelCharactersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MarvelCharactersApp)
            modules(appModule)
        }
    }
}