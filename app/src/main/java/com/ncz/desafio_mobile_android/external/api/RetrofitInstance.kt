package com.ncz.desafio_mobile_android.external.api
import com.ncz.desafio_mobile_android.external.config.Settings
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
     private val base: Settings = Settings()

     val baseUrl = base.baseUrl
     val publicKey = base.publicKey
     val timestamp = base.timestamp
     val hash = base.md5()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("$baseUrl/v1/public/characters?ts=$timestamp&apikey=$publicKey&hash=$hash")
            .addConverterFactory(GsonConverterFactory.create())
            .client(initOkHttpClient())
            .build()
    }

    private fun initOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor())

        client.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        return client.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

}