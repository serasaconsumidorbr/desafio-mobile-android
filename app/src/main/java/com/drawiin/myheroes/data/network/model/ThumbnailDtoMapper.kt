package com.drawiin.myheroes.data.network.model

import com.drawiin.myheroes.domain.util.DomainMapper
import com.drawiin.myheroes.domain.model.character.Thumbnail

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
