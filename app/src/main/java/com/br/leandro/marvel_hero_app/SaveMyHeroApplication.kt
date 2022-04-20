package com.br.leandro.marvel_hero_app

import com.br.leandro.marvel_hero_app.common.di.componet.DaggerSaveMyHeroApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SaveMyHeroApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSaveMyHeroApplicationComponent.factory().create(this)
    }
}