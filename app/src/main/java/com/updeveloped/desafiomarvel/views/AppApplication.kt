package com.updeveloped.desafiomarvel.views

import android.app.Application
import com.updeveloped.desafiomarvel.modules.DataModule
import com.updeveloped.desafiomarvel.modules.repositoryModule
import com.updeveloped.desafiomarvel.modules.uiModule
import com.updeveloped.desafiomarvel.modules.viewModelModule


import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinConfig()
    }

    private fun startKoinConfig(){
        startKoin {
            androidContext(this@AppApplication)
            DataModule.load()
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    uiModule
                )
            )
        }
    }
}