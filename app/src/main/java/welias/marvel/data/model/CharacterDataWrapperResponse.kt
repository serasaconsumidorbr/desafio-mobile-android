package welias.marvel.data.model

data class CharacterDataWrapperResponse(
    val data: CharacterDataContainerResponse
)

data class CharacterDataContainerResponse(
    val total: Long,
    val results: List<CharacterResponse>
)

data class CharacterResponse(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailResponse,
    val resourceURI: String,
    val comics: ComicsResponse,
    val series: ComicsResponse,
    val stories: StoriesResponse,
    val events: ComicsResponse,
    val urls: List<URLResponse>
)

data class ComicsResponse(
    val available: Long,
    val collectionURI: String,
    val items: List<ComicsItemResponse>,
    val returned: Long
)

data class ComicsItemResponse(
    val resourceURI: String,
    val name: String
)

data class StoriesResponse(
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItemResponse>,
    val returned: Long
)

data class StoriesItemResponse(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class ThumbnailResponse(
    val path: String,
    val extension: String
)

data class URLResponse(
    val type: String,
    val url: String
)
