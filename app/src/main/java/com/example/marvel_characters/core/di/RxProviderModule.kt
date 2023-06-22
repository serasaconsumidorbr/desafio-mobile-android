package com.example.marvel_characters.core.di

import com.example.marvel_characters.core.RxProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RxProviderModule {

    @Binds
    fun bindRxProvider(rxProvider: RxProvider.Impl): RxProvider
}