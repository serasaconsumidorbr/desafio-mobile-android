package com.example.marvel_app.framework.di

import android.content.Context
import androidx.room.Room
import com.example.marvel_app.framework.db.AppDatabase
import com.example.marvel_app.utils.Constants.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE_NAME
    ).build()


    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase) = appDatabase.favoriteDao()
}