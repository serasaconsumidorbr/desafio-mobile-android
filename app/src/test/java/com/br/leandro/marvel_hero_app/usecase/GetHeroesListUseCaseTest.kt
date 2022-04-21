package com.br.leandro.marvel_hero_app.usecase

import com.br.leandro.marvel_hero_app.features.common.ActionResult
import com.br.leandro.marvel_hero_app.features.common.Failure
import com.br.leandro.marvel_hero_app.features.common.Loading
import com.br.leandro.marvel_hero_app.features.common.Success
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.domain.hero.HeroRepository
import com.br.leandro.marvel_hero_app.domain.hero.usecase.GetHeroesListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetHeroesListUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var heroRepositoryMock: HeroRepository

    lateinit var getHeroesListUseCase: GetHeroesListUseCase

    private val heroOne = Hero(1, "hero1", "no thumb1")
    private val heroTwo = Hero(2, "hero2", "no thumb2")

    private var response: MutableList<ActionResult<List<Hero>>> = mutableListOf()

    @Before
    fun setUp() {
        response.clear()
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        getHeroesListUseCase = GetHeroesListUseCase(heroRepositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Request list of 2 heroes from repository then emit success `() {
        runBlockingTest {
            val listOfHeroes = listOf<Hero>(heroOne, heroTwo)
            val flowMock = flow<ActionResult<List<Hero>>> {
                emit(Loading(isLoading = true))
                advanceTimeBy(10)
                emit(Success(listOfHeroes))
                advanceTimeBy(10)
                emit(Loading(isLoading = false))
            }

            coEvery { heroRepositoryMock.getElementsFromApi(2) } returns flowMock

            getHeroesListUseCase.execute(2).collect { state ->
                response.add(state)
            }
            advanceTimeBy(2000)
            Assert.assertEquals(3, response.size)
            assert(response[0] is Loading)
            assert(response[1] is Success)
            assert(response[2] is Loading)
            val data = (response[1] as Success).data
            Assert.assertNotNull(data)
            assertEquals(listOfHeroes, data)
        }
    }

    @Test
    fun `Request list of 2 heroes from repository then emit failure `() {
        runBlockingTest {
            val listOfHeroes = listOf<Hero>(heroOne, heroTwo)
            val failure = Throwable()
            val flowMock = flow<ActionResult<List<Hero>>> {
                emit(Loading(isLoading = true))
                advanceTimeBy(10)
                emit(Failure(failure))
                advanceTimeBy(10)
                emit(Loading(isLoading = false))
            }

            coEvery { heroRepositoryMock.getElementsFromApi(2) } returns flowMock

            getHeroesListUseCase.execute(2).collect { state ->
                response.add(state)
            }
            advanceTimeBy(2000)
            Assert.assertEquals(3, response.size)
            assert(response[0] is Loading)
            assert(response[1] is Failure)
            assert(response[2] is Loading)
            val data = (response[1] as Failure).failure
            Assert.assertNotNull(data)
            Assert.assertNotEquals(listOfHeroes, data)
            Assert.assertEquals(failure, data)
        }
    }
}