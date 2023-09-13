package com.example.marvelapp.features.characterdetail.data.repository

import android.util.Log
import com.example.marvelapp.features.characterdetail.data.mapper.toCharacterDetailsModel
import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel
import com.example.marvelapp.framework.service.ApiClient
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
) : CharacterDetailRepository {

    override suspend fun getCharacterDetail(id: Int): CharacterDetailModel {
        try {
            val response = apiClient.getCharacterDetail(id)
            if (response.data.results.isNotEmpty()) {
                return response.data.results.first().toCharacterDetailsModel()
            }
        } catch (ex: Exception) {
            Log.d("exp_", ex.message.toString())
            throw RuntimeException()
        }
        return CharacterDetailModel()
    }
}