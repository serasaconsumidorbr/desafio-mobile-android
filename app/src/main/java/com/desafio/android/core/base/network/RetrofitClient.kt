package com.desafio.android.core.base.network

import com.desafio.android.domain.constants.Settings
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Settings.NETWORK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }
}