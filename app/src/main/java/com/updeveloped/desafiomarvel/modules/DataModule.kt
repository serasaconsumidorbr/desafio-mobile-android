package com.updeveloped.desafiomarvel.modules

import com.google.gson.GsonBuilder
import com.updeveloped.desafiomarvel.domain.repository.MarvelRepository
import com.updeveloped.desafiomarvel.data.service.MarvelService
import com.updeveloped.desafiomarvel.domain.repository.MarvelRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    private const val MARVEL_API_URL = "https://gateway.marvel.com/v1/public/"
    private val gsonBuilder = GsonBuilder()

    fun load() {
        loadKoinModules(marvelRepositoryModule() + networkModule())
    }

    private fun marvelRepositoryModule(): Module {
        return module {
            single<MarvelRepository> { MarvelRepositoryImpl(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<MarvelService> { createService(get()) }
            single { createOkHttpBuilder() }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(MARVEL_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()
            .create(T::class.java)
    }

    private fun createOkHttpBuilder(): OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}
