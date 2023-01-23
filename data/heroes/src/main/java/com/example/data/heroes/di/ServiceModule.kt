package com.example.data.heroes.di

import com.example.data.heroes.remote.HeroesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideHeroeApi(retrofit: Retrofit): HeroesApi {
        return retrofit.create(HeroesApi::class.java)
    }

}