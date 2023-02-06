package com.example.marvelheroes.data.datasource

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.api.CharactersServiceApi
import com.example.marvelheroes.data.retrofit.ServiceGenerator
import javax.inject.Inject


class CharactersRemoteDataSource @Inject constructor() {

    private var mService = ServiceGenerator.createServiceCoroutine(
        serviceClass = CharactersServiceApi::class.java,
        url = BuildConfig.MARVEL_URL
    )

    suspend fun getCharactersAsync(
        limit: Int,
        ts: String,
        apikey: String,
        hash: String,
    ) = mService.getCharactersAsync(limit, ts, apikey, hash).await()

}