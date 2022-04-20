package com.example.home_data.di

import android.content.Context
import com.example.home_data.R
import com.example.home_data.remote.mapper.*
import com.example.home_data.remote.mapper.impl.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeMappersModule {

    @Provides
    @Singleton
    fun providesCharacterThumbnailToImageUrlMapper(): CharacterThumbnailToImageUrlMapper =
        CharacterThumbnailToImageUrl()

    @Provides
    @Singleton
    fun providesCharacterSafeStringMapper(): CharacterSafeStringMapper =
        CharacterSafeString()

    @Provides
    @Singleton
    fun providesCharacterMapperDtoToCharacterMapper(
        @ApplicationContext appContext: Context,
        charStringMapper: CharacterSafeStringMapper,
        thumbnailToImageUrlMapper: CharacterThumbnailToImageUrlMapper
    ): CharacterDtoToCharacterMapper = CharacterDtoToCharacter(
        thumbnailMapper = thumbnailToImageUrlMapper,
        nullNameMessage = appContext.getString(R.string.no_name),
        nullDescriptionMessage = appContext.getString(R.string.no_description),
        safeStringMapper = charStringMapper
    )


}

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeMappersModuleBinder {

    @Binds
    @Singleton
    abstract fun bindsCharacterDataDtoToCharactersMapper(
        characterDataDtoToCharacter: CharactersDataDtoToCharacters,
    ): CharactersDataDtoToCharactersMapper
}