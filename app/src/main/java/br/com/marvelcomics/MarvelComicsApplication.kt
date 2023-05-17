package br.com.marvelcomics

import android.app.Application
import br.com.marvelcomics.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelComicsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelComicsApplication)
            modules(NetworkModule.dependencies)
        }
    }
}