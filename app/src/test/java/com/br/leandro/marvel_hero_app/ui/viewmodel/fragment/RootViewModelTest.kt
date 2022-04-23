package com.fernandohbrasil.marvelsquad.ui.viewmodel.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.marvelsquad.RxImmediateSchedulerRule
import com.fernandohbrasil.marvelsquad.datasource.MarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.db.model.CharacterEntity
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

private const val EXCEPTION = "Exception"

class RootViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MarvelRepository

    private lateinit var viewModel: RootViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RootViewModel(repository)
    }

    @Test
    fun rootMySquadStarted() {
        viewModel.start()
        assertEquals(RootMySquadStarted, viewModel.rootMySquadState.value)
    }

    @Test
    fun rootMySquadFinished() {
        viewModel.finish()
        assertEquals(RootMySquadFinished, viewModel.rootMySquadState.value)
    }

    @Test
    fun rootMySquadSuccess() {
        val characters = mutableListOf(CharacterEntity(0, "", ""))

        Mockito.`when`(repository.getMySquad())
            .thenReturn(Flowable.just(characters))

        viewModel.findMySquad()

        assertEquals(
            RootMySquadSuccess(characters),
            viewModel.rootMySquadState.value
        )
    }

    @Test
    fun rootMySquadError() {
        Mockito.`when`(repository.getMySquad())
            .thenReturn(Flowable.error(Throwable(EXCEPTION)))

        viewModel.findMySquad()

        assertEquals(
            RootMySquadError(EXCEPTION),
            viewModel.rootMySquadState.value
        )
    }

    @Test
    fun rootCharactersApiFinished() {
        viewModel.finish()
        assertEquals(RootCharactersApiFinished, viewModel.rootCharactersApiState.value)
    }

    @Test
    fun rootCharactersApiSuccess() {
//        val character = Character(0, "", Thumbnail("", ""), "")
//        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<Character>
//        val characters = Characters(Data(listOf(character)))
//
//        Mockito.`when`(repository.getCharactersApi(40))
//            .thenReturn(Flowable.just(characters))
//
//        viewModel.start()
//
//        assertEquals(
//            RootCharactersApiSuccess(pagedList),
//            viewModel.rootCharactersApiState.value
//        )
    }

    @Test
    fun rootCharactersApiError() {
//        Mockito.`when`(repository.getCharactersApi(40))
//            .thenReturn(Flowable.error(Throwable(EXCEPTION)))
//
//        viewModel.start()
//
//        assertEquals(
//            RootCharactersApiError(EXCEPTION),
//            viewModel.rootCharactersApiState.value
//        )
    }
}