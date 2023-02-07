package com.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.desafio.android.core.base.network.RetrofitClient
import com.desafio.android.core.base.storage.InternalDatabase
import com.desafio.android.repository.home.HomeRepository
import com.desafio.android.repository.home.sources.MarvelCharacterApi
import com.desafio.android.repository.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesHomeRepository(@ApplicationContext context: Context): HomeRepository = HomeRepositoryImpl(
        api = RetrofitClient.build(
            service = MarvelCharacterApi::class.java,
        ),
        database = Room.databaseBuilder(
            context,
            InternalDatabase::class.java, "my-database"
        ).build()
    )
}