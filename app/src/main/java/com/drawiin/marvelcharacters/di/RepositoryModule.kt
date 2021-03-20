package com.drawiin.marvelcharacters.di


import com.drawiin.marvelcharacters.data.network.model.CharacterDtoMapper
import com.drawiin.marvelcharacters.data.network.service.MavelService
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
        mavelService: MavelService,
        characterDtoMapper: CharacterDtoMapper
    ): CharactersRepository {
        return DefaultCharactersRepository(
            mavelService,
            characterDtoMapper
        )
    }
}