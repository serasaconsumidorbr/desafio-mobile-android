package com.br.leandro.marvel_hero_app

import android.app.Application
import com.br.leandro.marvel_hero_app.core.di.AppComponent
import com.br.leandro.marvel_hero_app.core.di.AppComponentProvider

class App : Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(application = this)
    }

}