package com.victorvgc.mymarvelheros.home.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.mymarvelheros.home.domain.data_source.HeroesDataSource
import com.victorvgc.mymarvelheros.test_utils.testHeroList
import com.victorvgc.mymarvelheros.test_utils.testHeroesPage
import com.victorvgc.mymarvelheros.test_utils.testHeroesPageEmpty
import com.victorvgc.mymarvelheros.test_utils.testPageOffset
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HeroesRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT
    private lateinit var sut: HeroesRepositoryImpl

    // MOCK
    private val mockLocalHeroDataSource = mockk<HeroesDataSource.Local>()
    private val mockRemoteHeroDataSource = mockk<HeroesDataSource.Remote>()

    @Before
    fun setup() {
        sut = HeroesRepositoryImpl(mockLocalHeroDataSource, mockRemoteHeroDataSource)
    }

    @Test
    fun `when get initial heroes then fetch from remote and stores locally`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getInitialHeroes() }.answers { testHeroList }
        coEvery { mockLocalHeroDataSource.saveInitialHeroes(any()) }.answers { }
        coEvery { mockLocalHeroDataSource.getInitialHeroes() }.answers { testHeroList }

        // act
        sut.getInitialHeroes()

        // assert
        coVerify(ordering = Ordering.SEQUENCE) {
            mockRemoteHeroDataSource.getInitialHeroes()
            mockLocalHeroDataSource.saveInitialHeroes(testHeroList)
            mockLocalHeroDataSource.getInitialHeroes()
        }
    }

    @Test
    fun `when get initial heroes fetches from remote is empty then return local data`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getInitialHeroes() }.answers { emptyList() }
        coEvery { mockLocalHeroDataSource.getInitialHeroes() }.answers { testHeroList }

        // act
        sut.getInitialHeroes()

        // assert
        coVerify(exactly = 0) {
            mockLocalHeroDataSource.saveInitialHeroes(testHeroList)
        }
    }

    @Test
    fun `when get initial heroes then return a list of heroes`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getInitialHeroes() }.answers { testHeroList }
        coEvery { mockLocalHeroDataSource.saveInitialHeroes(any()) }.answers { }
        coEvery { mockLocalHeroDataSource.getInitialHeroes() }.answers { testHeroList }

        // act
        val result = sut.getInitialHeroes()

        // assert
        assertEquals(testHeroList, result)
    }

    @Test
    fun `when get heroes page then fetch form remote and store in local database`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPage }
        coEvery { mockLocalHeroDataSource.saveHeroesPage(any()) }.answers { }
        coEvery { mockLocalHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPage }

        // act
        sut.getHeroesPage(testPageOffset)

        // assert
        coVerify(ordering = Ordering.SEQUENCE) {
            mockRemoteHeroDataSource.getHeroesPage(testPageOffset)
            mockLocalHeroDataSource.saveHeroesPage(testHeroesPage)
            mockLocalHeroDataSource.getHeroesPage(testPageOffset)
        }
    }

    @Test
    fun `when get heroes page returns empty then do not save heroes page locally`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPageEmpty }
        coEvery { mockLocalHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPage }

        // act
        sut.getHeroesPage(testPageOffset)

        // assert
        coVerify(exactly = 0) {
            mockLocalHeroDataSource.saveHeroesPage(testHeroesPage)
        }
    }

    @Test
    fun `when get heroes page then return heroes page`() = runTest {
        // arrange
        coEvery { mockRemoteHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPage }
        coEvery { mockLocalHeroDataSource.saveHeroesPage(any()) }.answers { }
        coEvery { mockLocalHeroDataSource.getHeroesPage(any()) }.answers { testHeroesPage }

        // act
        val result = sut.getHeroesPage(testPageOffset)

        // assert
        assertEquals(testHeroesPage, result)
    }
}