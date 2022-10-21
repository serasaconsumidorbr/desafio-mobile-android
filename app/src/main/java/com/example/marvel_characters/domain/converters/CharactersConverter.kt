package com.example.marvel_characters.domain.converters

import com.example.marvel_characters.domain.models.CharacterEntity
import com.example.marvel_characters.domain.models.CharacterImage
import com.example.marvel_characters.domain.models.Characters

object CharactersConverter {

    fun toEntity(characters: Characters): CharacterEntity{
        return CharacterEntity(
            id = characters.id,
            name = characters.name,
            description = characters.description,
            imagePath = characters.thumbnail.path,
            imageExtension = characters.thumbnail.extension
        )
    }

    fun fromEntity(characterEntity: CharacterEntity): Characters{
        return Characters(
            id = characterEntity.id,
            name = characterEntity.name,
            description = characterEntity.description,
            thumbnail = CharacterImage(
                characterEntity.imagePath,
                characterEntity.imageExtension)
        )
    }
}