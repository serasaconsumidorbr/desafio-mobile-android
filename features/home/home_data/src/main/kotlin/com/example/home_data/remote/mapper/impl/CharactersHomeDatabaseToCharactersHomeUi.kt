package com.example.home_data.remote.mapper.impl

import com.example.home_data.local.CharacterHomeEntity
import com.example.home_data.remote.mapper.CharactersHomeDatabaseToCharactersHomeUiMapper
import com.example.home_domain.model.CharacterHomeUiModel

class CharactersHomeDatabaseToCharactersHomeUi : CharactersHomeDatabaseToCharactersHomeUiMapper {
    override fun mapFrom(from: List<CharacterHomeEntity>): List<CharacterHomeUiModel> =
        from.map {
            CharacterHomeUiModel(
                name = it.name,
                imageUrl = it.imageUrl,
                description = it.description
            )
        }
}