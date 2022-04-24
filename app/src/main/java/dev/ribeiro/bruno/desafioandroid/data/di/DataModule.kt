package dev.ribeiro.bruno.desafioandroid.data.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dev.ribeiro.bruno.desafioandroid.data.repository.MarvelRepositoryImpl
import dev.ribeiro.bruno.desafioandroid.data.service.MarvelService
import dev.ribeiro.bruno.desafioandroid.domain.repository.MarvelRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object DataModule {

    private const val MARVEL_API_URL = "https://gateway.marvel.com/v1/public/"
    private const val OK_HTTP_TAG = "OKHTTP"

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
            single<MarvelService> { createService(get(), get()) }
            single { createGsonBuilder() }
            single { createOkHttpBuilder() }
        }
    }

    private inline fun <reified T> createService(
        factory: Gson,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(MARVEL_API_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }

    private fun createGsonBuilder(): Gson{
        return GsonBuilder().apply {
            setLenient()
            registerTypeAdapter(
                Date::class.java,
                JsonDeserializer<Date> { json, typeOfT, context ->
                    if (json.asJsonPrimitive.isNumber) {
                        Date(json.asJsonPrimitive.asLong * 1000)
                    } else {
                        null
                    }
                })
        }.create()
    }

    private fun createOkHttpBuilder(): OkHttpClient{
        val interceptor =  HttpLoggingInterceptor { Log.e(OK_HTTP_TAG, it) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

}