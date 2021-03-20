package com.drawiin.marvelcharacters.data.network.model

import com.drawiin.marvelcharacters.domain.model.character.Character
import com.drawiin.marvelcharacters.domain.util.DomainMapper

class CharacterDtoMapper(
    private val thumbnailDtoMapper: ThumbnailDtoMapper
) : DomainMapper<CharacterDto, Character> {
    override fun mapToDomainModel(model: CharacterDto): Character {
        return Character(
            description = model.description,
            id = model.id,
            name = model.name,
            thumbnail = model.thumbnail?.let { thumbnailDtoMapper.mapToDomainModel(it) }
        )
    }

    override fun mapFromDomainModel(domainModel: Character): CharacterDto {
        return CharacterDto(
            description = domainModel.description,
            id = domainModel.id,
            name = domainModel.name,
            thumbnail = domainModel.thumbnail?.let { thumbnailDtoMapper.mapFromDomainModel(it) }
        )
    }

    fun mapToDomainList(list: List<CharacterDto>): List<Character> {
        return list.map { mapToDomainModel(it) }
    }

    fun mapFromDomainList(list: List<Character>): List<CharacterDto> {
        return list.map { mapFromDomainModel(it) }
    }
}