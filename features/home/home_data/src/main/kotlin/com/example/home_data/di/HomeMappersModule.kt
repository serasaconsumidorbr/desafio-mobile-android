package com.example.home_data.di

import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_data.remote.mapper.impl.CharacterDtoToCharacter
import com.example.home_data.remote.mapper.impl.CharacterThumbnailToImageUrl
import com.example.home_data.remote.mapper.impl.CharactersDataDtoToCharacters
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeMappersModule {

    @Provides
    @Singleton
    fun providesCharacterThumbnailToImageUrlMapper(): CharacterThumbnailToImageUrlMapper =
        CharacterThumbnailToImageUrl()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeMappersModuleBinder {

    @Binds
    @Singleton
    abstract fun bindsCharacterMapperDtoToCharacterMapper(
        characterDtoToCharacter: CharacterDtoToCharacter,
    ): CharacterDtoToCharacterMapper

    @Binds
    @Singleton
    abstract fun bindsCharacterDataDtoToCharactersMapper(
        characterDataDtoToCharacter: CharactersDataDtoToCharacters,
    ): CharactersDataDtoToCharactersMapper
}