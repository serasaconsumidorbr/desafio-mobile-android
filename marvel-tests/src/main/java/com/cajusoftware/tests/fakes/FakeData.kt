package com.cajusoftware.tests.fakes

import androidx.paging.PagingData
import com.cajusoftware.marvelcharacters.data.database.dtos.ComicSummaryDto
import com.cajusoftware.marvelcharacters.data.database.dtos.ComicsDto
import com.cajusoftware.marvelcharacters.data.domain.Thumbnail
import com.cajusoftware.marvelcharacters.data.network.responses.*
import com.cajusoftware.marvelcharacters.utils.*

object FakeData {

    private val urlsList = listOf(
        UrlsResponse(
            "detail",
            "http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=aa25ad40fd9b97c4381f568f22d19c84"
        ),
        UrlsResponse(
            "wiki",
            "http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=aa25ad40fd9b97c4381f568f22d19c84"
        ),
        UrlsResponse(
            "comiclink",
            "http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=aa25ad40fd9b97c4381f568f22d19c84"
        ),
    )

    private val comicSummaryResponse = listOf(
        ComicSummaryResponse(
            "Avengers: The Initiative (2007) #14",
            "http://gateway.marvel.com/v1/public/comics/21366"
        )
    )
    private val storiesSummaryResponse =
        listOf(
            StoriesSummaryResponse(
                "Cover #19947",
                "cover",
                "http://gateway.marvel.com/v1/public/stories/19947"
            )
        )
    private val eventsSummaryResponse = listOf(
        EventsSummaryResponse(
            "Secret Invasion",
            "http://gateway.marvel.com/v1/public/events/269"
        )
    )
    private val seriesSummaryResponse = listOf(
        SeriesSummaryResponse(
            "Avengers: The Initiative (2007 - 2010)",
            "http://gateway.marvel.com/v1/public/series/1945"
        )
    )

    private val comicsResponse =
        ComicsResponse(
            12,
            "http://gateway.marvel.com/v1/public/characters/1011334/comics",
            comicSummaryResponse,
            12

        )

    private val storiesResponse =
        StoriesResponse(
            12,
            "http://gateway.marvel.com/v1/public/characters/1011334/comics",
            storiesSummaryResponse,
            12
        )

    private val eventsResponse =
        EventsResponse(
            12,
            "http://gateway.marvel.com/v1/public/characters/1011334/comics",
            eventsSummaryResponse,
            12

        )

    private val seriesResponse =
        SeriesResponse(
            12,
            "http://gateway.marvel.com/v1/public/characters/1011334/comics",
            seriesSummaryResponse,
            12

        )

    val character = com.cajusoftware.marvelcharacters.data.domain.Character(
        0,
        "3-D Man",
        "",
        "http://gateway.marvel.com/v1/public/characters/1011334",
        urlsList.asUrlDto().asUrls(),
        Thumbnail(
            "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
            "jpg"
        ),
        comicsResponse.asComicsDto().asComics(),
        storiesResponse.asStoriesDto().asStories(),
        eventsResponse.asEventsDto().asEvents(),
        seriesResponse.asSeriesDto().asSeries()
    )

    private val characterResponseList = listOf(
        CharacterResponse(
            0,
            "3-D Man",
            "",
            "2014-04-29T14:18:17-0400",
            "http://gateway.marvel.com/v1/public/characters/1011334",
            urlsList,
            ThumbnailResponse("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg"),
            comicsResponse,
            storiesResponse,
            eventsResponse,
            seriesResponse
        ),
        CharacterResponse(
            1,
            "A.I.M.",
            "AIM is a terrorist organization bent on destroying the world.",
            "2013-10-17T14:41:30-0400",
            "http://gateway.marvel.com/v1/public/characters/1009144",
            urlsList,
            ThumbnailResponse("http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec", "jpg"),
            comicsResponse,
            storiesResponse,
            eventsResponse,
            seriesResponse
        )
    )

    private val characterDataContainerResponse =
        CharacterDataContainerResponse(0, 100, 200, 50, characterResponseList)

    val characterDataWrapperResponse =
        CharacterDataWrapperResponse(
            0,
            "200",
            "© 2023, MARVEL",
            "Data provided by Marvel. © 2023 MARVEL",
            "<a href=\"http://marvel.com\">Data provided by Marvel. © 2023 MARVEL</a>",
            characterDataContainerResponse,
            "3jn5h456hb23bhu54b54h6h5h77h6jh87h9"
        )

    val carouselCharacter = characterResponseList.first().asCharacterDto(
        characterDataWrapperResponse.copyright, 1
    ).asCarouselCharacter()

    val carouselCharacterLast = characterResponseList.last().asCharacterDto(
        characterDataWrapperResponse.copyright, 1
    ).asCarouselCharacter()

    val carouselCharacterList = listOf(carouselCharacter, carouselCharacterLast)

    val carouselCharacterPaging = PagingData.from(carouselCharacterList)

    val comicsDtoString =
        "available=5-_collection_uri=http://gateway.marvel.com/v1/public/characters/1009203/comics-_items=[ComicSummaryDto(name=Wolverine (1988) #78, resourceUri=http://gateway.marvel.com/v1/public/comics/14201)-_ComicSummaryDto(name=Wolverine (1988) #84, resourceUri=http://gateway.marvel.com/v1/public/comics/14208)-_ComicSummaryDto(name=Wolverine (1988) #123, resourceUri=http://gateway.marvel.com/v1/public/comics/14063)-_ComicSummaryDto(name=X-Men Unlimited (1993) #8, resourceUri=http://gateway.marvel.com/v1/public/comics/18158)-_ComicSummaryDto(name=X-Men Unlimited (1993) #9, resourceUri=http://gateway.marvel.com/v1/public/comics/18159)]-_returned=5"

    val comicsDto = ComicsDto(
        available = 5,
        collectionUri = "http://gateway.marvel.com/v1/public/characters/1009203/comics",
        items = arrayListOf(
            ComicSummaryDto(
                name = "Wolverine (1988) #78",
                resourceUri = "http://gateway.marvel.com/v1/public/comics/14201"
            ),
            ComicSummaryDto(
                name = "Wolverine (1988) #84",
                resourceUri = "http://gateway.marvel.com/v1/public/comics/14208"
            ),
            ComicSummaryDto(
                name = "Wolverine (1988) #123",
                resourceUri = "http://gateway.marvel.com/v1/public/comics/14063"
            ),
            ComicSummaryDto(
                name = "X-Men Unlimited (1993) #8",
                resourceUri = "http://gateway.marvel.com/v1/public/comics/18158"
            ),
            ComicSummaryDto(
                name = "X-Men Unlimited (1993) #9",
                resourceUri = "http://gateway.marvel.com/v1/public/comics/18159"
            )
        ), returned = 5
    )
}