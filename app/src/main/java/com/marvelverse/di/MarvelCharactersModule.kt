package com.marvelverse.di

import android.content.Context
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.marvelverse.R
import com.marvelverse.data.datasources.FavoritesDbDataSource
import com.marvelverse.data.datasources.RemoteDataSource
import com.marvelverse.data.db.MarvelCharactersFavoriteDatabase
import com.marvelverse.data.network.MarvelAPI
import com.marvelverse.data.network.PicassoThumbnailService
import com.marvelverse.data.repositories.DefaultMarvelCharactersRepository
import com.marvelverse.domain.MarvelCharactersRepository
import com.marvelverse.domain.ThumbnailService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelCharactersModule {

    @Singleton
    @Provides
    fun provideMarvelApi(@ApplicationContext appContext: Context): MarvelAPI {
        return MarvelAPI(
            apiKey = appContext.getString(R.string.api_key),
            privateKey = appContext.getString(R.string.private_key),
        )
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(marvelAPI: MarvelAPI): RemoteDataSource {
        val retrofit = Retrofit.Builder()
            .baseUrl(marvelAPI.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        val applicationScope = CoroutineScope(SupervisorJob())
        return MarvelCharactersFavoriteDatabase.getDatabase(appContext,
            applicationScope,
            "marvel_characters_favorite_database")
    }

    @Singleton
    @Provides
    fun provideFavoritesDbDatasource(favoriteDatabase: RoomDatabase): FavoritesDbDataSource {
        return FavoritesDbDataSource(((favoriteDatabase) as MarvelCharactersFavoriteDatabase).marvelCharacterFavoritesDao())
    }

    @Singleton
    @Provides
    fun provideMarvelCharactersRepository(
        remoteDataSource: RemoteDataSource,
        marvelAPI: MarvelAPI,
        favoritesDbDataSource: FavoritesDbDataSource,
    ): MarvelCharactersRepository {
        return DefaultMarvelCharactersRepository(remoteDataSource, favoritesDbDataSource, marvelAPI)
    }

    @Singleton
    @Provides
    fun provideThumbnailService(): ThumbnailService {
        return PicassoThumbnailService()
    }
}