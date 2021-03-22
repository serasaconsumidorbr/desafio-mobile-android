package com.drawiin.marvelcharacters.di


import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.drawiin.marvelcharacters.domain.interactors.GetCarousel
import com.drawiin.marvelcharacters.domain.interactors.GetCharacters
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
        charactersRepository: CharactersRepository
    ): GetCharacters {
        return GetCharacters(charactersRepository)
    }

    @Singleton
    @Provides
    fun provideCarousel(
        charactersRepository: CharactersRepository
    ): GetCarousel {
        return GetCarousel(charactersRepository)
    }
}