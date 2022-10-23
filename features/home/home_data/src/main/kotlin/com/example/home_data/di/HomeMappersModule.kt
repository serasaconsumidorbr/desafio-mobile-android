package com.example.home_data.di

import android.content.Context
import com.example.home_data.R
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharacterSafeStringMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_data.remote.mapper.SslPathMapper
import com.example.home_data.remote.mapper.impl.CharacterDtoToCharacter
import com.example.home_data.remote.mapper.impl.CharacterSafeString
import com.example.home_data.remote.mapper.impl.CharacterThumbnailToImageUrl
import com.example.home_data.remote.mapper.impl.CharactersDataDtoToCharacters
import com.example.home_data.remote.mapper.impl.SslPathMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeMappersModule {

    @Provides
    fun providesCharacterSafeStringMapper(): CharacterSafeStringMapper = CharacterSafeString()

    @Provides
    fun providesSslPathMapper(): SslPathMapper = SslPathMapperImpl()

    @Provides
    fun providesCharacterMapperDtoToCharacterMapper(
        @ApplicationContext appContext: Context,
        charStringMapper: CharacterSafeStringMapper,
        thumbnailToImageUrlMapper: CharacterThumbnailToImageUrlMapper,
    ): CharacterDtoToCharacterMapper = CharacterDtoToCharacter(
        thumbnailMapper = thumbnailToImageUrlMapper,
        nullNameMessage = appContext.getString(R.string.no_name),
        nullDescriptionMessage = appContext.getString(R.string.no_description),
        safeStringMapper = charStringMapper
    )


}

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeMappersModuleBinder {

    @Binds
    abstract fun bindsCharacterDataDtoToCharactersMapper(
        characterDataDtoToCharacter: CharactersDataDtoToCharacters,
    ): CharactersDataDtoToCharactersMapper

    @Binds
    abstract fun bindsSslPathMapper(
        characterThumbnailToImageUrl: CharacterThumbnailToImageUrl,
    ): CharacterThumbnailToImageUrlMapper
}
