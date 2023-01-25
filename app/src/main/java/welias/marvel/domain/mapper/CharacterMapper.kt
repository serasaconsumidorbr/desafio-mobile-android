package welias.marvel.domain.mapper

import welias.marvel.data.model.*
import welias.marvel.domain.model.*

fun CharacterResponse.toCharacterDomainItem(): CharacterDomain {
    return CharacterDomain(
        id = this.id,
        name = this.name,
        description = this.description,
        modified = this.modified,
        resourceURI = this.resourceURI,
        urls = this.urls.toUrlDomain(),
        thumbnail = this.thumbnail.toThumbnailDomain(),
        comics = this.comics.toComicsDomain(),
        stories = this.stories.toStoriesDomain(),
        events = this.events.toComicsDomain(),
        series = this.series.toComicsDomain()
    )
}

fun List<URLResponse>.toUrlDomain(): List<URLDomain> {
    val transform: (URLResponse) -> URLDomain = {
        URLDomain(
            type = it.type,
            url = it.url
        )
    }

    return this.map(transform)
}

fun ThumbnailResponse.toThumbnailDomain(): ThumbnailDomain {
    return ThumbnailDomain(
        path = this.path,
        extension = this.extension
    )
}

fun ComicsResponse.toComicsDomain(): ComicsDomain {
    return ComicsDomain(
        available = this.available,
        collectionURI = this.collectionURI,
        items = this.items.toListComicsItemDomain(),
        returned = this.returned
    )
}

fun List<ComicsItemResponse>.toListComicsItemDomain(): List<ComicsItemDomain> {
    val transform: (ComicsItemResponse) -> ComicsItemDomain = {
        ComicsItemDomain(
            resourceURI = it.resourceURI,
            name = it.name
        )
    }

    return this.map(transform)
}

fun StoriesResponse.toStoriesDomain(): StoriesDomain {
    return StoriesDomain(
        available = this.available,
        collectionURI = this.collectionURI,
        items = this.items.toListStoriesItemDomain(),
        returned = this.returned
    )
}

fun List<StoriesItemResponse>.toListStoriesItemDomain(): List<StoriesItemDomain> {
    val transform: (StoriesItemResponse) -> StoriesItemDomain = {
        StoriesItemDomain(
            resourceURI = it.resourceURI,
            name = it.name,
            type = it.type
        )
    }

    return this.map(transform)
}
