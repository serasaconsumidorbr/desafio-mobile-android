package com.example.marvelapp.features.characterdetail.data.repository

import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel

interface CharacterDetailRepository {

    suspend fun getCharacterDetail(id: Int): CharacterDetailModel

}