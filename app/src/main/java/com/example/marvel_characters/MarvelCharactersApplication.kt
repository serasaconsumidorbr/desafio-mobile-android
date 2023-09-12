package com.example.marvel_characters

import android.app.Application
import com.example.marvel_characters.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCharactersApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelCharactersApplication)
            modules(appModule)
        }
    }
}