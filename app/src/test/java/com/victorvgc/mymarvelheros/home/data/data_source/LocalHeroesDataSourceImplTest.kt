package com.victorvgc.mymarvelheros.home.data.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.mymarvelheros.home.data.database.HeroDao
import com.victorvgc.mymarvelheros.test_utils.testHeroList
import com.victorvgc.mymarvelheros.test_utils.testHeroesPage
import com.victorvgc.mymarvelheros.test_utils.testLocalHero
import com.victorvgc.mymarvelheros.test_utils.testLocalHeroList
import com.victorvgc.mymarvelheros.test_utils.testLocalHeroWithOffset
import com.victorvgc.mymarvelheros.test_utils.testPageOffset
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
class LocalHeroesDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT
    private lateinit var sut: LocalHeroesDataSourceImpl

    // MOCK
    private val mockHeroDao = mockk<HeroDao>()

    @Before
    fun setup() {
        sut = LocalHeroesDataSourceImpl(mockHeroDao)
    }

    @Test
    fun `when save initial heroes then insert into database`() = runTest {
        // arrange
        coEvery { mockHeroDao.insertHero(any()) }.answers { }

        // act
        sut.saveInitialHeroes(testHeroList)

        // assert
        coVerify(exactly = testHeroList.size) { mockHeroDao.insertHero(testLocalHero) }
    }

    @Test
    fun `when get initial heroes then fetch from database`() = runTest {
        // arrange
        coEvery { mockHeroDao.getInitialHeroes() }.answers { testLocalHeroList }

        // act
        val result = sut.getInitialHeroes()

        // assert
        assertEquals(testHeroList, result)
    }

    @Test
    fun `when save heroes page then insert heroes into database`() = runTest {
        //arrange
        coEvery { mockHeroDao.insertHero(any()) }.answers { }

        // act
        sut.saveHeroesPage(testHeroesPage)

        // assert
        coVerify(exactly = testHeroList.size) { mockHeroDao.insertHero(testLocalHeroWithOffset) }
    }

    @Test
    fun `when get heroes page then fetch from database`() = runTest {
        // arrange
        coEvery { mockHeroDao.getHeroPage(any()) }.answers { testLocalHeroList }

        // act
        val result = sut.getHeroesPage(testPageOffset)

        // assert
        assertEquals(testHeroesPage, result)
    }
}
