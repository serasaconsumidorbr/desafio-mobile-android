package com.cajusoftware.marvelcharacters.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepository
import com.cajusoftware.tests.fakes.FakeData
import com.cajusoftware.tests.rules.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CharacterViewModelTest {

    private lateinit var viewModel: CharacterViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: CharacterRepository = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()

    private val upperCarouselObserver = mockk<Observer<List<CarouselCharacter?>>>()
    private val carouselObserver = mockk<Observer<PagingData<CarouselCharacter>>>()

    private val upperCarouselSlot = slot<List<CarouselCharacter>>()
    private val carouselSlot = slot<PagingData<CarouselCharacter>>()

    private val carouselCharacterList = arrayListOf<CarouselCharacter>()
    private var carouselCharacterPaging: PagingData<CarouselCharacter>? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() = runTest {
        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery { repository.upperCarouselCharacters } coAnswers { flow { emit(FakeData.carouselCharacterList) } }
        coEvery { repository.carouselCharacters } coAnswers { flow { emit(FakeData.carouselCharacterPaging) } }
        coEvery { repository.getCharactersRandomly() } coAnswers { flow { emit(FakeData.carouselCharacterPaging) } }

        viewModel = CharacterViewModel(repository)

        dispatcher.run {
            viewModel.upperCarouselItems.observeForever(upperCarouselObserver)
            viewModel.carouselItems.observeForever(carouselObserver)
        }


        coEvery { upperCarouselObserver.onChanged(capture(upperCarouselSlot)) } coAnswers {
            carouselCharacterList.addAll(upperCarouselSlot.captured)
        }

        coEvery { carouselObserver.onChanged(capture(carouselSlot)) } coAnswers {
            carouselCharacterPaging = carouselSlot.captured
        }

        dispatcher.run {
            viewModel.getCharactersToUpperCarousel()
            viewModel.getCharacters()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterViewModel_upperCarouselItems_verifyCarouselCharacter() {
        dispatcher.run {
            assertEquals(FakeData.carouselCharacterList, carouselCharacterList)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterViewModel_carouselItems_verifyCarouselCharacter() {
        dispatcher.run {
            assertNotNull(carouselCharacterPaging)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterViewModel_shouldShowPlaceholder_verifyValue() {
        dispatcher.run {
            assertNotNull(viewModel.shouldShowPlaceholder.value)
            assert(viewModel.shouldShowPlaceholder.value!!)
        }
    }
}