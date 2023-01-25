package welias.marvel.domain.usecase

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
import welias.marvel.domain.mapper.toCharacterDomainItem
import welias.marvel.domain.repository.AppRepository
import welias.marvel.core.utils.Utils
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest {

    private val repository: AppRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    private fun setupUseCase(
        spyOnIt: Boolean = false
    ) = GetCharactersUseCase(
        repository = repository
    ).let {
        if (spyOnIt) {
            spyk(it)
        } else {
            it
        }
    }

    @Test
    fun `getListCharacters Should return a flow list characters domain When repository return flow list characters domain`() =
        runTest {
            // Given
            val useCase = setupUseCase()
            val offset = Utils.offset
            val list = Utils.getCharacterDataWrapperResponse().data.results
            val resultDataSource = flowOf(list.map { it.toCharacterDomainItem() })
            val expected = flowOf(list.map { it.toCharacterDomainItem() })

            // When
            coEvery { repository.getListCharacters(offset = offset) } returns resultDataSource
            val response = useCase.execute(offset = offset)

            // Then
            assertEquals(expected.first(), response.first())
        }
}
