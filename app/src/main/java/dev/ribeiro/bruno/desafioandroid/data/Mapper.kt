package dev.ribeiro.bruno.desafioandroid.data

import dev.ribeiro.bruno.desafioandroid.data.model.response.CharacterResponse
import dev.ribeiro.bruno.desafioandroid.data.model.response.ResultResponse
import dev.ribeiro.bruno.desafioandroid.data.model.response.ThumbnailResponse
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.entities.ListInformation
import dev.ribeiro.bruno.desafioandroid.domain.entities.Thumbnail

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