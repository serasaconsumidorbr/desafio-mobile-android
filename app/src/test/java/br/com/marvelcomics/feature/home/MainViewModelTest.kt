package br.com.marvelcomics.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marvelcomics.base.util.Resource
import br.com.marvelcomics.data.repository.MarvelCharRepository
import br.com.marvelcomics.model.MarvelCharacter
import br.com.marvelcomics.rule.MainDispatcherRule
import com.jraska.livedata.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    private val repository = mockk<MarvelCharRepository>(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `loading should be true when fetching more data`() {
        val loading = viewModel.loading.test()
        val error = viewModel.error.test()

        coEvery { repository.fetchCharacters(any()) } returns flow { emit(Resource.Loading()) }
        viewModel.fetchMarvelChars()
        verify(exactly = 1) { repository.fetchCharacters(any()) }

        loading.assertValue(true)
        error.assertValue(false)
        assert(viewModel.isInitialFetch())
    }

    @Test
    fun `error should be true when api returns error`() {
        val loading = viewModel.loading.test()
        val error = viewModel.error.test()

        coEvery { repository.fetchCharacters(any()) } returns flow { emit(Resource.Error(Exception())) }
        viewModel.fetchMarvelChars()
        verify(exactly = 1) { repository.fetchCharacters(any()) }

        loading.assertValue(false)
        error.assertValue(true)
        assert(viewModel.isInitialFetch())
    }

    @Test
    fun `characters should have content when api returns success`() {
        val loading = viewModel.loading.test()
        val error = viewModel.error.test()
        val characters = viewModel.characters.test()

        val mockedData = mockk<MarvelCharacter>()
        val list = mutableListOf<MarvelCharacter>()
        repeat(20) { list.add(mockedData) }

        coEvery { repository.fetchCharacters(any()) } returns flow { emit(Resource.Success(list)) }
        viewModel.fetchMarvelChars()
        verify(exactly = 1) { repository.fetchCharacters(any()) }

        loading.assertValue(false)
        error.assertValue(false)

        characters.assertValue { it.isNotEmpty() }

        assert(!viewModel.isInitialFetch())
    }
}