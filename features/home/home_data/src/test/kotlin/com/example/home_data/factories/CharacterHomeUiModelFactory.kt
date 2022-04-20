package com.example.home_data.factories

import com.example.home_domain.model.CharacterHomeUiModel

object CharacterHomeUiModelFactory {
    fun make(
        name: String = "name",
        description: String = "description",
        imageUrl: String = "imageurl.com"
    ): CharacterHomeUiModel = CharacterHomeUiModel(
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}