package com.updeveloped.desafiomarvel.data

import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.domain.entities.ListInformation
import com.updeveloped.desafiomarvel.domain.entities.Thumbnail
import com.updeveloped.desafiomarvel.data.response.dto.CharacterResponse
import com.updeveloped.desafiomarvel.data.response.dto.ResultResponse
import com.updeveloped.desafiomarvel.data.response.dto.ThumbnailResponse

fun CharacterResponse.toEntity(): ListInformation = ListInformation().apply {
    count = this@toEntity.data.count
    limit = this@toEntity.data.limit
    offset = this@toEntity.data.offset
    results = this@toEntity.data.results.toListEntity()
    total = this@toEntity.data.total
}

fun List<ResultResponse>.toListEntity(): List<CharacterDetail> {
    val resultCharacterDetail: List<CharacterDetail> = this.map {
        it.toEntity()
    }
    return resultCharacterDetail
}

fun ResultResponse.toEntity(): CharacterDetail = CharacterDetail().apply {
    id = this@toEntity.id
    name = this@toEntity.name
    description = this@toEntity.description
    modified = this@toEntity.modified
    thumbnail = this@toEntity.thumbnail.toEntity()
}

fun ThumbnailResponse.toEntity(): Thumbnail = Thumbnail().apply {
    extension = this@toEntity.extension
    path = this@toEntity.path
}