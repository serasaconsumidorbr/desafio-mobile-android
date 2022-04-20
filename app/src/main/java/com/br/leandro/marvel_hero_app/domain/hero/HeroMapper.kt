package com.br.leandro.marvel_hero_app.domain.hero

import javax.inject.Inject


class HeroMapper @Inject constructor() : Mapper<HeroResponse, Hero> {
    override fun to(from: HeroResponse): Hero {
        return from.run {
            Hero(
                id = id,
                name = name,
                thumbnail = thumbnail.path,
                extension = thumbnail.extension,
                description = description
            )
        }
    }
}