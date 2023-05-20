package br.com.marvelcomics.feature.home

import br.com.marvelcomics.KoinTestRule
import br.com.marvelcomics.base.util.UiException
import br.com.marvelcomics.data.repository.MarvelCharRepository
import io.mockk.spyk
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class MainActivityTest {

    private val repository = spyk<MarvelCharRepository>()
    private val module = module {
        single { repository }
        single { MainViewModel(get()) }
    }

    @get:Rule
    val koinTest = KoinTestRule(listOf(module))


    @Test
    fun shouldLoadingMessageWhenReturnsSuccess() {
        executeTest {
            withFetchCharSuccess()
        } launch {} check {
            checkRecyclerViewTitleItem(0, "Em destaque")
            checkRecyclerViewTitleItem(2, "Mais personagens")
            checkRecyclerViewContainsItem(1, "name1")
            checkRecyclerViewContainsItem(3, "name6")
            checkErrorButtonIsNotDisplayed()
            checkErrorMessageIsNotDisplayed()
            checkProgressBarMessageIsNotDisplayed()
        }
    }

    @Test
    fun shouldLoadingMessageWhenReturnsLoading() {
        executeTest {
            withFetchCharLoading()
        } launch {} check {
            checkErrorButtonIsNotDisplayed()
            checkErrorMessageIsNotDisplayed()
            checkProgressBarMessageIsDisplayed()
        }
    }

    @Test
    fun shouldShowGenericMessageWhenReturnsError() {
        executeTest {
            withFetchHeroesError(UiException.GenericUiException())
        } launch {} check {
            checkErrorButtonIsDisplayed()
            checkErrorMessage("Falha ao buscar mais pesonagens")
            checkErrorMessageIsDisplayed()
            checkProgressBarMessageIsNotDisplayed()
        }
    }

    @Test
    fun shouldShowTimeoutMessageWhenReturnsError() {
        executeTest {
            withFetchHeroesError(UiException.TimeoutException())
        } launch {} check {
            checkErrorButtonIsDisplayed()
            checkErrorMessage("Conexão expirada, tente novamente")
            checkErrorMessageIsDisplayed()
            checkProgressBarMessageIsNotDisplayed()
        }
    }

    @Test
    fun shouldShowServerErrorMessageWhenReturnsError() {
        executeTest {
            withFetchHeroesError(UiException.GenericApiException())
        } launch {} check {
            checkErrorButtonIsDisplayed()
            checkErrorMessage("Problema de conexão, tente novamente")
            checkErrorMessageIsDisplayed()
            checkProgressBarMessageIsNotDisplayed()
        }
    }
}