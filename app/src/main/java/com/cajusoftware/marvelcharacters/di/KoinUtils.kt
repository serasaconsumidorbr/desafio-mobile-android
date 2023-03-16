package com.cajusoftware.marvelcharacters.di

import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.sources.ModelsPagingMediator
import com.cajusoftware.marvelcharacters.data.network.services.MarvelApiService
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepository
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepositoryImpl
import com.cajusoftware.marvelcharacters.ui.details.CharacterDetailViewModel
import com.cajusoftware.marvelcharacters.ui.home.CharacterViewModel
import retrofit2.Retrofit

fun provideMarvelService(retrofit: Retrofit): MarvelApiService =
    retrofit.create(MarvelApiService::class.java)

fun provideCharacterRepository(
    characterDao: CharacterDao,
    remoteMediator: ModelsPagingMediator
): CharacterRepository =
    CharacterRepositoryImpl(characterDao, remoteMediator)

fun provideCharacterViewModel(characterRepository: CharacterRepository): CharacterViewModel =
    CharacterViewModel(characterRepository)

fun provideCharacterDetailViewModel(characterRepository: CharacterRepository): CharacterDetailViewModel =
    CharacterDetailViewModel(characterRepository)