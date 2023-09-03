package com.example.marvel_app.framework.di

import com.example.core.features.characters.usecase.GetCharactersUseCase
import com.example.core.features.characters.usecase.GetCharactersUseCaseImpl
import com.example.core.features.details.usecase.GetCategoriesUseCase
import com.example.core.features.details.usecase.GetCategoriesUseCaseImpl
import com.example.core.features.favorites.usecase.AddFavoriteUseCase
import com.example.core.features.favorites.usecase.AddFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsGetCharactersUseCase(useCaseImpl: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindsGetCategoriesUseCase(useCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase

    @Binds
    fun bindsAddFavoriteUseCase(useCaseImpl: AddFavoriteUseCaseImpl): AddFavoriteUseCase
}