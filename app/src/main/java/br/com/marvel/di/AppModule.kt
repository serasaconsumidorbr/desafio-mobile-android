package br.com.marvel.di

import br.com.marvel.data.MarvelApi
import br.com.marvel.utilities.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)
}