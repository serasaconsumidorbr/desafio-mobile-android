package com.br.leandro.marvel_hero_app.common.di.module

import android.app.Application
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object ApplicationModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRequestManagerGlide(application: Application) = Glide.with(application)

    @Singleton
    @JvmStatic
    @Provides
    fun provideDatabase(application: Application) = SaveMyHeroDatabase.getInstance(application)

    @Singleton
    @JvmStatic
    @Provides
    fun provideHeroDao(database: SaveMyHeroDatabase) = database.heroDao()
}

