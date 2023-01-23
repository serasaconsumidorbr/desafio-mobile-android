package com.example.app.di

import android.app.Application
import com.example.app.local.AppDatabase
import com.example.data.heroes.local.dao.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) = AppDatabase.Builder(application).build()

    @Provides
    fun provideHeroDao(appDatabase: AppDatabase): HeroDao {
        return appDatabase.heroDao()
    }

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO

}
