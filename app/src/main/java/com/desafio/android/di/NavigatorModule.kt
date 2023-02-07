package com.desafio.android.di

import com.desafio.android.core.base.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {
    @Singleton
    @Provides
    fun providesNavigator(): Navigator = Navigator()
}