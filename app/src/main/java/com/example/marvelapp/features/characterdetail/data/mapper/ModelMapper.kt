package com.example.marvelapp.features.characterdetail.data.mapper

import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails

fun CharacterDetails.toCharacterDetailsModel(): CharacterDetailModel {
    val characterDetailModel = CharacterDetailModel()
    characterDetailModel.id = this.id
    characterDetailModel.name = this.name
    characterDetailModel.description = this.description
    characterDetailModel.thumbnail = this.thumbnail.path.plus(".").plus(this.thumbnail.extension)

    return characterDetailModel
}