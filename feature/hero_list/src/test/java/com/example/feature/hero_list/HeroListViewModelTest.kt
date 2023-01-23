package com.example.feature.hero_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.model.Page
import com.example.domain.heroes.model.Thumbnail
import com.example.domain.heroes.usecase.LoadCharactersUseCase
import com.example.feature.hero_list.state.HeroViewState
import com.example.utils.CoroutineContextProvider
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.*
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


@RunWith(JUnit4::class)
class HeroListViewModelTest {

    private lateinit var heroListViewModel: HeroListViewModel

    private val loadCharactersUseCase: LoadCharactersUseCase = mock()

    @OptIn(ExperimentalTime::class)
    @ExperimentalCoroutinesApi
    @Test
    fun `should observe state Success`() = runTest {

        val heroes = listOf(
            Hero(1, "Kate Bishop", "", "2014-04-29T14:18:17-0400", Thumbnail("", ""), "")
        )


        loadCharactersUseCase.stub {
            onBlocking { invoke(0) } doReturn flowOf(
                Page(
                    0,
                    nextPage = 0,
                    heroes
                )
            )
        }

        heroListViewModel = HeroListViewModel(loadCharactersUseCase, TestContextProvider())

        val result = arrayListOf<HeroViewState>()
        val job = launch {
            heroListViewModel.uiState.toList(result) //now it should work
        }

        heroListViewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(HeroViewState.Success(Page(
                0,
                nextPage = 0,
                heroes
            )))
            cancelAndIgnoreRemainingEvents()
        }
        job.cancel()


    }


}

@ExperimentalCoroutinesApi
class TestContextProvider : CoroutineContextProvider() {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    override val main: CoroutineContext = testCoroutineDispatcher
    override val io: CoroutineContext = testCoroutineDispatcher
}