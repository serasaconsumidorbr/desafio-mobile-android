package com.cajusoftware.marvelcharacters

import android.app.Application
import android.os.Build
import com.cajusoftware.marvelcharacters.di.dataModules
import com.cajusoftware.marvelcharacters.di.networkModules
import com.cajusoftware.marvelcharacters.di.uiModule
import com.cajusoftware.marvelcharacters.utils.NetworkUtils.networkConnectivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this))
        startKoin {
            androidContext(this@MarvelApplication)
            modules(dataModules, networkModules, uiModule)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            networkConnectivity(this)
        }
    }
}