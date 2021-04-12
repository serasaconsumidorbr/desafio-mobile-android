package com.desafio.marvel.feature.characters.data.repository

import com.desafio.marvel.commons.api.ApiClient
import com.desafio.marvel.commons.api.BaseCallback
import com.desafio.marvel.feature.characters.data.api.CharactersService
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.domain.model.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersRepositoryImpl(mClient: ApiClient) : CharactersRepository {

    private val mService = mClient.createService(CharactersService::class.java)

    override fun getCharacters(offset:Int, callback: BaseCallback<CharactersResponse>) {
        mService.getCharacters(20, offset).enqueue(
            object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    callback.onError(t.message.toString())
                }

                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    response.body()?.let { callback.onSuccess(it.data) }
                }
            })
    }
}