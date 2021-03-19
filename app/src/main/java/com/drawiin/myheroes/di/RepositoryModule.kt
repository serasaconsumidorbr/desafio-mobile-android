package com.drawiin.myheroes.di


import com.drawiin.myheroes.data.network.model.CharacterDtoMapper
import com.drawiin.myheroes.data.network.service.HeroesService
import com.drawiin.myheroes.data.repository.DefaultHeroesRepository
import com.drawiin.myheroes.domain.boundarys.HeroesRepository
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