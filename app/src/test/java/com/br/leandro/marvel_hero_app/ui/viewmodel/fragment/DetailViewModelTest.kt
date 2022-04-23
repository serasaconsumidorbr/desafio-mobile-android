package com.fernandohbrasil.marvelsquad.ui.viewmodel.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.marvelsquad.RxImmediateSchedulerRule
import com.fernandohbrasil.marvelsquad.datasource.MarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.network.model.*
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

private const val EXCEPTION = "Exception"

class DetailViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MarvelRepository

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun characterHireStateDbStarted() {
        viewModel.start()
        assertEquals(CharacterHireStateDbStarted, viewModel.characterHireStateDb.value)
    }

    @Test
    fun characterHireStateDbFinished() {
        viewModel.finish()
        assertEquals(CharacterHireStateDbFinished, viewModel.characterHireStateDb.value)
    }

    @Test
    fun characterHireStateDbHired() {
//        //Find the CharacterDetail Api
//        val character = Character(0, "", Thumbnail("", ""), "")
//        val characters = Characters(Data(listOf(character)))
//
//        Mockito.`when`(repository.getCharacterByIdApi(character.id))
//            .thenReturn(Single.just(characters))
//
//        viewModel.findCharacterApi(character.id)
//        //Try to find Character Database
//        val characterEntity = CharacterEntity(0, "", "")
//
//        Mockito.`when`(repository.getCharacterByIdDatabase(character.id))
//            .thenReturn(Single.error(Throwable(EXCEPTION)))
//
//        viewModel.findCharacterDb(character.id)
//
//        Mockito.`when`(repository.hireCharacter(characterEntity))
//            .thenReturn(Completable.complete())
//
//        viewModel.hireOrFireCharacter()
//
//        assertEquals(
//            CharacterHireStateDbHired,
//            viewModel.characterHireStateDb.value
//        )
    }

    @Test
    fun characterHireStateDbFired() {

    }

    @Test
    fun characterHireStateDbError() {

    }

    @Test
    fun characterApiStarted() {
        viewModel.start()
        assertEquals(CharacterApiStarted, viewModel.characterStateApi.value)
    }

    @Test
    fun characterApiFinished() {
        viewModel.finish()
        assertEquals(CharacterApiFinished, viewModel.characterStateApi.value)
    }

    @Test
    fun characterApiSuccess() {
        val character = Character(0, "", Thumbnail("", ""), "")
        val characters = Characters(Data(listOf(character)))

        Mockito.`when`(repository.getCharacterByIdApi(character.id))
            .thenReturn(Single.just(characters))

        viewModel.findCharacterApi(character.id)

        assertEquals(
            CharacterApiSuccess(character),
            viewModel.characterStateApi.value
        )
    }

    @Test
    fun characterApiError() {
        val character = Character(0, "", Thumbnail("", ""), "")

        Mockito.`when`(repository.getCharacterByIdApi(character.id))
            .thenReturn(Single.error(Throwable(EXCEPTION)))

        viewModel.findCharacterApi(character.id)

        assertEquals(
            CharacterApiError(EXCEPTION),
            viewModel.characterStateApi.value
        )
    }

    @Test
    fun comicsApiStarted() {
        viewModel.start()
        assertEquals(ComicsApiStarted, viewModel.comicsStateApi.value)
    }

    @Test
    fun comicsApiFinished() {
        viewModel.finish()
        assertEquals(ComicsApiFinished, viewModel.comicsStateApi.value)
    }

    @Test
    fun comicsApiSuccess() {
        val thumbnail = Thumbnail("", "")
        val character = Character(0, "", thumbnail, "")
        val comics = Comics(DataComic(0, listOf(Comic(0, "", thumbnail))))

        Mockito.`when`(repository.getComicsByCharacterIdApi(character.id))
            .thenReturn(Flowable.just(comics))

        viewModel.findComics(character.id)

        assertEquals(
            ComicsApiSuccess(comics),
            viewModel.comicsStateApi.value
        )
    }

    @Test
    fun comicsApiError() {
        val thumbnail = Thumbnail("", "")
        val character = Character(0, "", thumbnail, "")

        Mockito.`when`(repository.getComicsByCharacterIdApi(character.id))
            .thenReturn(Flowable.error(Throwable(EXCEPTION)))

        viewModel.findComics(character.id)

        assertEquals(
            ComicsApiError(EXCEPTION),
            viewModel.comicsStateApi.value
        )
    }
}