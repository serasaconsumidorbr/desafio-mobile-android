package com.example.home_data.remote.mapper.impl

import com.example.home_data.local.CharacterHomeEntity
import com.example.home_data.remote.mapper.CharactersHomeUiToCharactersHomeDatabaseMapper
import com.example.home_domain.model.CharacterHomeUiModel

class CharactersHomeUiToCharactersHomeDatabase :
    CharactersHomeUiToCharactersHomeDatabaseMapper {
    override fun mapFrom(from: List<CharacterHomeUiModel>): List<CharacterHomeEntity> = from.map {
        CharacterHomeEntity(
            name = it.name,
            description = it.description,
            imageUrl = it.imageUrl
        )
    }
}