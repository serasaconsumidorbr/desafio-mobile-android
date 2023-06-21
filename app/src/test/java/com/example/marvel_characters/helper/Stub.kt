package com.example.marvel_characters.helper

import com.example.marvel_characters.data.dto.CharacterDTO
import com.example.marvel_characters.data.dto.CharacterDataContainerDTO
import com.example.marvel_characters.data.dto.CharacterDataWrapperDTO
import com.example.marvel_characters.data.dto.ImageDTO
import com.example.marvel_characters.domain.model.Character

object Stub {
    val imageDTO = ImageDTO("path", "extension")
    val characterDTO = CharacterDTO(1, "name", "desc", imageDTO)
    val characterDataContainerDTO = CharacterDataContainerDTO(0, 20, 200, listOf(characterDTO))
    val characterDataWrapperDTO = CharacterDataWrapperDTO(200, characterDataContainerDTO)

    val character = Character(1, "name", "desc", imageDTO.path.orEmpty())
}