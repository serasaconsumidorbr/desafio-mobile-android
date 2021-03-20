package com.drawiin.marvelcharacters.di


import com.drawiin.marvelcharacters.data.network.model.CharacterDtoMapper
import com.drawiin.marvelcharacters.data.network.service.MarvelClient
import com.drawiin.marvelcharacters.data.repository.DefaultCharactersRepository
import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
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
        marvelClient: MarvelClient,
        characterDtoMapper: CharacterDtoMapper
    ): CharactersRepository {
        return DefaultCharactersRepository(
            marvelClient,
            characterDtoMapper
        )
    }
}