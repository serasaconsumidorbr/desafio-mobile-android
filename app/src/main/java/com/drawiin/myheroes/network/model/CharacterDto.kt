package com.drawiin.myheroes.network.model


import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.domain.util.FromDomainObject
import com.drawiin.myheroes.domain.util.ToDomainObject
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto? = null
) : ToDomainObject<Character> {
    override fun toDomainObject() = Character(
        description = description,
        id = id,
        name = name,
        thumbnail = thumbnail?.toDomainObject()
    )

    companion object : FromDomainObject<CharacterDto, Character> {
        override fun fromDomainObject(domainModel: Character) = CharacterDto(
            description = domainModel.description,
            id = domainModel.id,
            name = domainModel.name,
            thumbnail = domainModel.thumbnail?.let { ThumbnailDto.fromDomainObject(it) }
        )
    }
}