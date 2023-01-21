package com.example.domain.heroes

import app.cash.turbine.test
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.model.Page
import com.example.domain.heroes.model.Thumbnail
import com.example.domain.heroes.repository.HeroRepository
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
class LoadHeroesUseCaseTest {

    private val repo: HeroRepository = mock()


    @Test
    fun `Should return characters`() = runBlocking {

        val heroes = listOf(
            Hero(1, "Kate Bishop", "", "2014-04-29T14:18:17-0400", Thumbnail("", ""), "")
        )

        repo.stub {
            onBlocking { getHeroes(0) } doReturn flowOf(Page(0, nextPage = 0, heroes))
        }

        val flow: Flow<Page> = LoadCharactersUseCase(repo).invoke(0)
        flow.test {
            Assert.assertTrue(awaitItem().hero == heroes)
            awaitComplete()
        }
    }
}