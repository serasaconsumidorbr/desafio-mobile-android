package br.com.marvelcomics.data.dto


data class MarvelCharDto(
    val id: Long?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)

data class MarvelCharListResponse(
    val results: List<MarvelCharDto>
)

data class Thumbnail(
    val path: String?,
    val extension: String?,
)