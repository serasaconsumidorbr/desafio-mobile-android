package com.drawiin.myheroes.di


import com.drawiin.myheroes.network.model.CharacterDtoMapper
import com.drawiin.myheroes.network.service.HeroesService
import com.drawiin.myheroes.repository.DefaultHeroesRepository
import com.drawiin.myheroes.repository.HeroesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHeroesRepository(
        heroesService: HeroesService,
        characterDtoMapper: CharacterDtoMapper
    ): HeroesRepository {
        return DefaultHeroesRepository(
            heroesService,
            characterDtoMapper
        )
    }
}