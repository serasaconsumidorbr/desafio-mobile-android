package dev.ribeiro.bruno.desafioandroid

import android.app.Application
import dev.ribeiro.bruno.desafioandroid.data.di.DataModule
import dev.ribeiro.bruno.desafioandroid.domain.di.DomainModule
import dev.ribeiro.bruno.desafioandroid.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinConfig()
    }

    private fun startKoinConfig(){
        startKoin {
            androidContext(this@App)
        }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }

}