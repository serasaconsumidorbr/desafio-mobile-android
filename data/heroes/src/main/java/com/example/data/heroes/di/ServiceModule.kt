package com.example.data.heroes.di

import com.example.data.heroes.remote.HeroesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {

    @Provides
    fun provideHeroeApi(retrofit: Retrofit): HeroesApi {
        return retrofit.create(HeroesApi::class.java)
    }

}