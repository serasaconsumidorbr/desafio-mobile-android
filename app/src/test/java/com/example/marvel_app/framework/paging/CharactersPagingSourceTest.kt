package com.example.marvel_app.framework.paging

import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingSource
import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.framework.network.response.DataWrapperResponse
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.example.marvel_app.utils.factory.DataWrapperResponseFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDatasource: CharactersRemoteDatasource<DataWrapperResponse>

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val characterFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setup() {
        charactersPagingSource = CharactersPagingSource(remoteDatasource, "")
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        //Arrange
        whenever(remoteDatasource.fetchCharacters(any()))
            .thenReturn(dataWrapperResponseFactory.create())

        //Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                false
            )
        )

        //Assert
        val expected = listOf(
            characterFactory.create(CharacterFactory.Hero.ThreeDMan),
            characterFactory.create(CharacterFactory.Hero.ABomb)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`()= runTest {
        //Arrange
        val exception = RuntimeException()
        whenever(remoteDatasource.fetchCharacters(any()))
            .thenThrow(exception)

        //Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                false
            )
        )

        //Assert
        assertEquals(PagingSource.LoadResult.Error<Int, Character>(exception), result)
    }
}