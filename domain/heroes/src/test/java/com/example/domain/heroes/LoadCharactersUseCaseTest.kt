package com.example.domain.heroes

import app.cash.turbine.test
import com.example.domain.heroes.model.Character
import com.example.domain.heroes.model.Thumbnail
import com.example.domain.heroes.repository.CharacterRepository
import com.example.domain.heroes.usecase.LoadCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub



@RunWith(JUnit4::class)
class LoadCharactersUseCaseTest {

    private val repo: CharacterRepository = mock()


    @Test
    fun `Should return characters`() = runBlocking {

        val characters = listOf(
            Character(1, "Kate Bishop", "", "2014-04-29T14:18:17-0400", Thumbnail("", ""), "")
        )

        repo.stub {
            onBlocking { getCharacters(0) } doReturn flowOf(characters)
        }

        val flow: Flow<List<Character>> = LoadCharactersUseCase(repo).invoke(0)
        flow.test {
            Assert.assertTrue(awaitItem() == characters)
            awaitComplete()
        }
    }
}