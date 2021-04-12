package com.desafio.marvel.feature.characters.data.datasource

import android.content.SharedPreferences
import com.desafio.marvel.commons.utils.Constants
import com.google.gson.Gson
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse

class CharactersLocalDataSource(private val mPreferences: SharedPreferences): CharactersDataSource {

    override fun saveCharacters(mCharactersList: CharactersResponse): Boolean {
        val data = Gson().toJson(mCharactersList)
        return mPreferences
            .edit()
            .putString(Constants.USER_LOCAL_DATA_SOURCE, data)
            .commit()
    }

    override fun getCharactersLocal(): CharactersResponse {
        return Gson().fromJson(
            mPreferences.getString(Constants.USER_LOCAL_DATA_SOURCE, ""),
            CharactersResponse::class.java
        )
    }

    override fun statusCharacters(): Boolean {
        val response = mPreferences.getString(Constants.USER_LOCAL_DATA_SOURCE, "")

        return !response.isNullOrEmpty()
    }

    override fun deleteCharacters(): Boolean {
        return mPreferences
            .edit()
            .remove(Constants.USER_LOCAL_DATA_SOURCE)
            .commit()
    }
}