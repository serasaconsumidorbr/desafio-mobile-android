package br.com.marvelcomics.di

import br.com.marvelcomics.base.extensions.resolveRetrofit
import br.com.marvelcomics.base.interceptor.AuthInterceptor
import br.com.marvelcomics.base.interceptor.DataUnwrapperInterceptor
import br.com.marvelcomics.data.remote.MarvelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val AUTH_INTERCEPTOR = "AUTH_INTERCEPTOR"
    private const val DATA_UNWRAP_INTERCEPTOR = "DATA_UNWRAP_INTERCEPTOR"

    val dependencies = module {
        single<Retrofit> { provideRetrofit(get()) }
        single<OkHttpClient> {
            provideOkHttpClient(
                get(named(AUTH_INTERCEPTOR)),
                get(named(DATA_UNWRAP_INTERCEPTOR))
            )
        }

        single<AuthInterceptor>(named(AUTH_INTERCEPTOR)) { AuthInterceptor() }
        single<DataUnwrapperInterceptor>(named(DATA_UNWRAP_INTERCEPTOR)) { DataUnwrapperInterceptor() }

        single<MarvelApi> { resolveRetrofit(get()) }
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    private fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        dataUnwrapperInterceptor: DataUnwrapperInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(authInterceptor)
        okHttpClient.addInterceptor(dataUnwrapperInterceptor)
        okHttpClient.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return okHttpClient.build()
    }
}