package com.drawiin.marvelcharacters.data.network.model

import com.drawiin.marvelcharacters.domain.util.DomainMapper
import com.drawiin.marvelcharacters.domain.model.character.Thumbnail

class ThumbnailDtoMapper : DomainMapper<ThumbnailDto, Thumbnail> {
    override fun mapToDomainModel(model: ThumbnailDto): Thumbnail {
        return Thumbnail(
            extension = model.extension,
            path = model.path
        )
    }

    override fun mapFromDomainModel(domainModel: Thumbnail): ThumbnailDto {
        return ThumbnailDto(
            extension = domainModel.extension,
            path = domainModel.path
        )
    }

}
