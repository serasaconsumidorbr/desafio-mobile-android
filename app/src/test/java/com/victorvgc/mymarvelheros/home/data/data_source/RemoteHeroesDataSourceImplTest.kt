package com.victorvgc.mymarvelheros.home.data.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.mymarvelheros.BuildConfig
import com.victorvgc.mymarvelheros.home.data.service.HeroesService
import com.victorvgc.mymarvelheros.home.data.utils.DateUtils
import com.victorvgc.mymarvelheros.home.data.utils.HashUtils
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage
import com.victorvgc.mymarvelheros.test_utils.testHash
import com.victorvgc.mymarvelheros.test_utils.testHeroList
import com.victorvgc.mymarvelheros.test_utils.testHeroesPage
import com.victorvgc.mymarvelheros.test_utils.testPageOffset
import com.victorvgc.mymarvelheros.test_utils.testPageOffsetInitial
import com.victorvgc.mymarvelheros.test_utils.testPageSizeLimit
import com.victorvgc.mymarvelheros.test_utils.testRemoteHeroResponse
import com.victorvgc.mymarvelheros.test_utils.testTimeStamp
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RemoteHeroesDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT
    private lateinit var sut: RemoteHeroesDataSourceImpl

    // MOCK
    private val mockHeroesService = mockk<HeroesService>()
    private val mockHashUtils = mockk<HashUtils>()
    private val mockDateUtils = mockk<DateUtils>()

    @Before
    fun setup() {
        sut = RemoteHeroesDataSourceImpl(mockHeroesService, mockHashUtils, mockDateUtils)
    }

    @Test
    fun `when get initial heroes then fetch from service with no offset`() =
        runTest {
            // arrange
            coEvery {
                mockHeroesService.getCharacters(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            }.answers { Response.success(testRemoteHeroResponse) }
            every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
            every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

            // act
            sut.getInitialHeroes()

            // assert
            coVerify(exactly = 1) {
                mockHeroesService.getCharacters(
                    apiKey = BuildConfig.API_KEY,
                    timestamp = testTimeStamp,
                    hash = testHash,
                    offset = testPageOffsetInitial,
                    limit = testPageSizeLimit
                )
            }
        }

    @Test
    fun `when get initial heroes then build hash with timestamp, api key and private key with md5`() =
        runTest {
            // arrange
            coEvery {
                mockHeroesService.getCharacters(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            }.answers { Response.success(testRemoteHeroResponse) }
            every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
            every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

            // act
            sut.getInitialHeroes()

            // assert
            verify(exactly = 1) {
                mockHashUtils.getMD5From(
                    testTimeStamp,
                    BuildConfig.PRIVATE_KEY,
                    BuildConfig.API_KEY
                )
            }
        }

    @Test
    fun `when get initial heroes then return a list of heroes`() = runTest {
        // arrange
        coEvery {
            mockHeroesService.getCharacters(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }.answers { Response.success(testRemoteHeroResponse) }
        every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
        every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

        // act
        val result = sut.getInitialHeroes()

        // assert
        assertEquals(testHeroList, result)
    }

    @Test
    fun `when get heroes page then fetch from service with the right offset`() = runTest {
        // arrange
        coEvery {
            mockHeroesService.getCharacters(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }.answers { Response.success(testRemoteHeroResponse) }
        every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
        every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

        // act
        sut.getHeroesPage(testPageOffset)

        // assert
        coVerify(exactly = 1) {
            mockHeroesService.getCharacters(
                apiKey = BuildConfig.API_KEY,
                timestamp = testTimeStamp,
                hash = testHash,
                offset = testPageOffset,
                limit = HeroesPage.DEFAULT_PAGE_SIZE
            )
        }
    }

    @Test
    fun `when get heroes page then build hash with timestamp, api key and private key with md5`() =
        runTest {
            // arrange
            coEvery {
                mockHeroesService.getCharacters(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            }.answers { Response.success(testRemoteHeroResponse) }
            every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
            every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

            // act
            sut.getHeroesPage(testPageOffset)

            // assert
            verify(exactly = 1) {
                mockHashUtils.getMD5From(
                    testTimeStamp,
                    BuildConfig.PRIVATE_KEY,
                    BuildConfig.API_KEY
                )
            }
        }

    @Test
    fun `when get heroes page then return a hero page with the heroes list`() = runTest {
        // arrange
        coEvery {
            mockHeroesService.getCharacters(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }.answers { Response.success(testRemoteHeroResponse) }
        every { mockHashUtils.getMD5From(any(), any(), any()) }.answers { testHash }
        every { mockDateUtils.getCurrentTimestamp() }.answers { testTimeStamp }

        // act
        val result = sut.getHeroesPage(testPageOffset)

        // assert
        assertEquals(testHeroesPage, result)
    }
}