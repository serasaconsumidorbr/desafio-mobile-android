package welias.marvel.core.utils

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker
import welias.marvel.data.model.*
import welias.marvel.domain.mapper.toCharacterDomainItem
import welias.marvel.presentation.mapper.toCharacterUi

object Utils {

    private val faker: Faker = faker {}

    // Constants
    const val offset = 20
    const val total: Long = 100

    // UI
    fun getCharactersUI() = getCharactersDomain().map {
        it.toCharacterUi()
    }

    // Domain
    fun getCharactersDomain() = getListCharactersResponse().map {
        it.toCharacterDomainItem()
    }

    // Response
    fun getCharacterDataWrapperResponse(): CharacterDataWrapperResponse {
        return CharacterDataWrapperResponse(
            data = getCharacterDataContainerResponse()
        )
    }

    fun getCharacterDataContainerResponse(): CharacterDataContainerResponse {
        return CharacterDataContainerResponse(
            total = total,
            results = getListCharactersResponse()
        )
    }

    fun getListCharactersResponse(): List<CharacterResponse> {
        val characterResponse = mutableListOf<CharacterResponse>()

        repeat(5) {
            characterResponse.add(
                CharacterResponse(
                    id = faker.random.nextLong(),
                    name = faker.name.toString(),
                    description = faker.name.toString(),
                    modified = faker.name.toString(),
                    thumbnail = getThumbnailResponse(),
                    resourceURI = faker.name.toString(),
                    comics = getComicsResponse(),
                    series = getComicsResponse(),
                    stories = getStoriesResponse(),
                    events = getComicsResponse(),
                    urls = getListUrlResponse()
                )
            )
        }

        return characterResponse.toList()
    }

    fun getThumbnailResponse(): ThumbnailResponse {
        return ThumbnailResponse(
            path = faker.name.toString(),
            extension = faker.name.toString()
        )
    }

    fun getComicsResponse(): ComicsResponse {
        return ComicsResponse(
            available = faker.random.nextLong(),
            collectionURI = faker.name.toString(),
            items = getListComicsItemResponse(),
            returned = faker.random.nextLong()
        )
    }

    fun getStoriesResponse(): StoriesResponse {
        return StoriesResponse(
            available = faker.random.nextLong(),
            collectionURI = faker.name.toString(),
            items = getListStoriesItemResponse(),
            returned = faker.random.nextLong()
        )
    }

    fun getListComicsItemResponse(): List<ComicsItemResponse> {
        val listComicsItemResponse = mutableListOf<ComicsItemResponse>()

        repeat(5) {
            listComicsItemResponse.add(
                ComicsItemResponse(
                    resourceURI = faker.name.toString(),
                    name = faker.name.toString()
                )
            )
        }

        return listComicsItemResponse.toList()
    }

    fun getListStoriesItemResponse(): List<StoriesItemResponse> {
        val listStoriesItemResponse = mutableListOf<StoriesItemResponse>()

        repeat(5) {
            listStoriesItemResponse.add(
                StoriesItemResponse(
                    resourceURI = faker.name.toString(),
                    name = faker.name.toString(),
                    type = faker.name.toString()
                )
            )
        }

        return listStoriesItemResponse.toList()
    }

    fun getListUrlResponse(): List<URLResponse> {
        val listURLResponse = mutableListOf<URLResponse>()

        repeat(5) {
            listURLResponse.add(
                URLResponse(
                    type = faker.name.toString(),
                    url = faker.name.toString()
                )
            )
        }

        return listURLResponse.toList()
    }
}
