package com.drawiin.myheroes.di


import com.drawiin.myheroes.repository.HeroesRepository
import com.drawiin.myheroes.useCases.GetHeroes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHeroes(
        heroesRepository: HeroesRepository
    ): GetHeroes {
        return GetHeroes(heroesRepository)
    }
}