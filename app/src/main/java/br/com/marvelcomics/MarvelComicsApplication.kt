package br.com.marvelcomics

import android.app.Application
import br.com.marvelcomics.di.AppModule
import br.com.marvelcomics.di.DatabaseModule
import br.com.marvelcomics.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelComicsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelComicsApplication)
            modules(AppModule.dependencies, NetworkModule.dependencies, DatabaseModule.dependencies)
        }
    }
}