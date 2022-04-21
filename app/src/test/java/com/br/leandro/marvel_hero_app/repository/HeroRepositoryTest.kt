package com.br.leandro.marvel_hero_app.repository

import com.br.leandro.marvel_hero_app.features.common.ActionResult
import com.br.leandro.marvel_hero_app.features.common.Failure
import com.br.leandro.marvel_hero_app.features.common.Loading
import com.br.leandro.marvel_hero_app.features.common.Success
import com.br.leandro.marvel_hero_app.data.local.HeroDao
import com.br.leandro.marvel_hero_app.data.remote.MarvelService
import com.br.leandro.marvel_hero_app.data.remote.model.ApiResponse
import com.br.leandro.marvel_hero_app.data.remote.model.ApiResponseJsonAdapter
import com.br.leandro.marvel_hero_app.data.remote.model.HeroResponse
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.domain.hero.HeroMapper
import com.br.leandro.marvel_hero_app.domain.hero.HeroRepository
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException


class HeroRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var marvelService: MarvelService

    @MockK
    lateinit var heroMapper: HeroMapper

    @MockK
    lateinit var heroDao: HeroDao

    private lateinit var heroRepository: HeroRepository
    private val heroOne = Hero(1, "hero1", "no thumb1")
    private val heroTwo = Hero(2, "hero2", "no thumb2")
    private val listOfHeroes = listOf(heroOne, heroTwo)
    private var heroMock: ApiResponse<HeroResponse>? = null
    private val moshi = Moshi.Builder().build()
    private val apiResponse =
        ApiResponseJsonAdapter<HeroResponse>(moshi, arrayOf(HeroResponse::class.java))
    private var response: MutableList<ActionResult<List<Hero>>> = mutableListOf()

    @Before
    fun setUp() {
        response.clear()
        heroMock = apiResponse.fromJson(HERO_RESPONSE_JSON_STRING)
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        heroRepository = HeroRepository(heroMapper, marvelService, heroDao)
        Assert.assertNotNull(heroRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Repository only request once api service with successful response`() {
        runBlockingTest {
            heroMock?.let {
                coEvery { marvelService.requestHeroes(offset = 1) } returns it
                coEvery { heroMapper.to(from = it.data.results) } returns listOfHeroes

                heroRepository.getElementsFromApi(1).collect { states ->
                    response.add(states)
                }
                advanceTimeBy(1_000)
                coVerify(exactly = 1) {
                    marvelService.requestHeroes(offset = 1)
                    heroMapper.to(from = it.data.results)
                }
                Assert.assertEquals(3, response.size)
                assert(response[0] is Loading)
                assert(response[1] is Success)
                assert(response[2] is Loading)
            }
        }
    }

    @Test
    fun `Repository only request once api service with network failure`() {
        runBlockingTest {
            coEvery { marvelService.requestHeroes(offset = 1) } throws IOException()
            coEvery { heroMapper.to(from = heroMock?.data!!.results) }

            heroRepository.getElementsFromApi(1).collect {
                response.add(it)
            }
            advanceTimeBy(1_000)
            coVerify(exactly = 1) {
                marvelService.requestHeroes(offset = 1)
            }
            Assert.assertEquals(3, response.size)
            assert(response[0] is Loading)
            assert(response[1] is Failure)
            assert(response[2] is Loading)
        }
    }
}