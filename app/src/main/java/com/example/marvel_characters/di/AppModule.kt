package com.example.marvel_characters.di


import android.content.Context
import com.example.marvel_characters.Constants
import com.example.marvel_characters.database.CharacterDatabase
import com.example.marvel_characters.database.CharactersLocalDataSource
import com.example.marvel_characters.database.MarvelDao
import com.example.marvel_characters.network.CharactersRemoteDataSource
import com.example.marvel_characters.network.MarvelApiService
import com.example.marvel_characters.repository.Repository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Repository.getRepository(get(), get())
    }

    single { provideRetrofit() }
    single { provideApiService(get()) }
    single { CharactersRemoteDataSource(get()) }
    single { CharactersLocalDataSource(get(), Dispatchers.IO) }
    single { provideCharactersDao( androidContext()) }

}
private fun provideCharactersDao(context: Context): MarvelDao =
    CharacterDatabase.getInstance(context).marvelDao


private fun provideRetrofit(): Retrofit {
    val gson = GsonBuilder().create()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(Constants.BASE_API_URL)
        .build()
}

private fun provideApiService(retrofit: Retrofit): MarvelApiService =
    retrofit.create(MarvelApiService::class.java)
