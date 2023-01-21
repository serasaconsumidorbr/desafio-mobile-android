package com.example.data.heroes.local.mapper

import com.example.data.heroes.local.entity.HeroEntity
import com.example.data.heroes.local.entity.Thumbnail
import com.example.domain.heroes.model.Hero


fun List<HeroEntity>.toDomain(): List<Hero> {
    return this.map {
        it.toDomain()
    }
}


fun List<Hero>.toEntity(): List<HeroEntity> {
    return this.map {
        it.toEntity()
    }
}

internal fun Hero.toEntity(): HeroEntity {
    return HeroEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = Thumbnail(thumbnail.path, thumbnail.extension),
        resourceURI = resourceURI,
    )
}

fun HeroEntity.toDomain(): Hero {
    return Hero(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = com.example.domain.heroes.model.Thumbnail(thumbnail.path, thumbnail.extension),
        resourceURI = resourceURI,
    )
}