package com.br.leandro.marvel_hero_app.common.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidInjectionModule::class,
        FragmentBuilderModule::class,
        NetworkModule::class
    ]
)
interface SaveMyHeroApplicationComponent : AndroidInjector<SaveMyHeroApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): SaveMyHeroApplicationComponent
    }
}