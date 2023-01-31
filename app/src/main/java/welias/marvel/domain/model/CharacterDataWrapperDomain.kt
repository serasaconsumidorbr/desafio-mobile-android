package welias.marvel.domain.model

data class CharacterDomain(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailDomain,
    val resourceURI: String,
    val comics: ComicsDomain,
    val series: ComicsDomain,
    val stories: StoriesDomain,
    val events: ComicsDomain,
    val urls: List<URLDomain>
)

data class ComicsDomain(
    val available: Long,
    val collectionURI: String,
    val items: List<ComicsItemDomain>,
    val returned: Long
)

data class ComicsItemDomain(
    val resourceURI: String,
    val name: String
)

data class StoriesDomain(
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItemDomain>,
    val returned: Long
)

data class StoriesItemDomain(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class ThumbnailDomain(
    val path: String,
    val extension: String
)

data class URLDomain(
    val type: String,
    val url: String
)
