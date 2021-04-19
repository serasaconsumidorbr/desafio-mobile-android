package com.challenge.marvelcharacters.network

import com.challenge.marvelcharacters.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(getLogging())
        .addInterceptor(AuthInterceptor())
        .build()

     private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
         .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun<T> buildService(service: Class<T>) : T{
        return retrofit.create(service)
    }

    private fun getLogging() : HttpLoggingInterceptor  {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return  logging
    }

}