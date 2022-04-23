package com.br.leandro.marvel_hero_app

import android.app.Application
import com.br.leandro.marvel_hero_app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {

    private val appComponent: MutableList<Module> =
        mutableListOf(networkModule, databaseModule, viewModelModule, repositoryModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appComponent)
        }
    }
}