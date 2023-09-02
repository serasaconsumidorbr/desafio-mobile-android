package com.example.marvel_app.framework.di

import com.example.core.features.characters.usecase.GetCharactersUseCase
import com.example.core.features.characters.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCaseImpl: GetCharactersUseCaseImpl): GetCharactersUseCase
}