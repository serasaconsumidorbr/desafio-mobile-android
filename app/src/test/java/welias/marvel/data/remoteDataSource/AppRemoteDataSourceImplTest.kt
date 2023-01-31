package welias.marvel.data.remoteDataSource

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import welias.marvel.data.datasource.remote.AppRemoteDataSourceImpl
import welias.marvel.data.service.AppService
import welias.marvel.core.utils.Utils
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AppRemoteDataSourceImplTest {

    private val service: AppService = mockk(relaxed = true)

    private fun setupRemoteDataSourceImpl(
        spyOnIt: Boolean = false
    ) = AppRemoteDataSourceImpl(service).let {
        if (spyOnIt) {
            spyk(it)
        } else {
            it
        }
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getListCharacters Should return a flow list characters response When service return CharacterDataWrapperResponse`() =
        runTest {
            // Given
            val remoteDataSourceImpl = setupRemoteDataSourceImpl()
            val offset = Utils.offset
            val result = Utils.getCharacterDataWrapperResponse()
            val expected = flowOf(result.data.results)

            // When
            coEvery { service.getListCharacters(offset = offset) } returns result
            val response = remoteDataSourceImpl.getListCharacters(offset = offset)

            // Then
            assertEquals(expected.first(), response.first())
        }
}
