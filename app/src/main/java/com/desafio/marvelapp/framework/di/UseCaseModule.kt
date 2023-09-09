package com.example.marvelapp.framework.di

import com.project.core.usecase.GetCharactersUseCaseImpl
import com.project.core.usecase.IGetCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCase: GetCharactersUseCaseImpl) : IGetCharactersUseCase
}