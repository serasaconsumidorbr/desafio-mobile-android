package com.drawiin.myheroes.di


import com.drawiin.myheroes.domain.boundarys.HeroesRepository
import com.drawiin.myheroes.domain.interactors.GetCarousel
import com.drawiin.myheroes.domain.interactors.GetHeroes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetHeroes(
        heroesRepository: HeroesRepository
    ): GetHeroes {
        return GetHeroes(heroesRepository)
    }

    @Singleton
    @Provides
    fun provideCarousel(
        heroesRepository: HeroesRepository
    ): GetCarousel {
        return GetCarousel(heroesRepository)
    }
}