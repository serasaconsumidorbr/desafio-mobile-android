package com.example.testeapp.di

import android.content.Context
import com.example.testeapp.common.ApiUtils
import com.example.testeapp.data.CharacterDao
import com.example.testeapp.data.CharacterRoom
import com.example.testeapp.data.repositores.LocalRepository
import com.example.testeapp.data.repositores.LocalRepositoryImpl
import com.example.testeapp.data.repositores.RemoteRepository
import com.example.testeapp.data.repositores.RemoteRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ModuleInjector {

    companion object {
        val baseUrl = "https://gateway.marvel.com/"
    }

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor;
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder()
            .build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CharacterRoom {
        return CharacterRoom.getInstance(appContext)
    }

    @Provides
    fun provideCharacterDao(appDatabase: CharacterRoom): CharacterDao {
        return appDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(retrofit: Retrofit, apiUtils: ApiUtils): RemoteRepository {
        return RemoteRepositoryImpl(retrofit, apiUtils)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(characterDao: CharacterDao): LocalRepository {
        return LocalRepositoryImpl(characterDao)
    }

    @Provides
    @Singleton
    fun ApiUtils(): ApiUtils {
        return ApiUtils
    }
}