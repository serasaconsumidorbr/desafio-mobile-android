package com.challenge.marvelcharacters.builders

import com.challenge.marvelcharacters.model.Character
import com.challenge.marvelcharacters.model.Image

class CharacterBuilder{
    private var id : Int = 1
    private var name : String = "3-D Man"
    private var description : String = ""
    private var image = ImageBuilder().build()

    fun setId(id : Int) = apply {
        this.id = id
    }

    fun setName(name : String) = apply {
        this.name = name
    }
    fun setDescription(description : String) = apply {
        this.description = description
    }

    fun setName(image : Image) = apply {

        this.image = image
    }

    fun build() = Character(id, name, description, image)




}