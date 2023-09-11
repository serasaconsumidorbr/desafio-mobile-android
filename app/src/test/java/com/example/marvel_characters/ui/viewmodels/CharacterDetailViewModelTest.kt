package com.example.marvel_characters.ui.viewmodels

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.*
import org.junit.Test


class MarvelCharactersUIStateTest
{
    @Test
    fun shouldReturnCanRequestNewCharactersPageTrue()
    {
        val marvelCharactersUIState=MarvelCharactersUIState(hasNextPage=true, loading = false  )
        assertThat(marvelCharactersUIState.canRequestNewCharactersPage, `is`(true))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenLoading()
    {
        val marvelCharactersUIState=MarvelCharactersUIState(hasNextPage=true, loading = true  )
        assertThat(marvelCharactersUIState.canRequestNewCharactersPage, `is`(false))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenHasNotNextPage()
    {
        val marvelCharactersUIState=MarvelCharactersUIState(hasNextPage=false, loading = true  )
        assertThat(marvelCharactersUIState.canRequestNewCharactersPage, `is`(false))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenHasNotNextPageAndLoadingAreFalse()
    {
        val marvelCharactersUIState=MarvelCharactersUIState(hasNextPage=false, loading = false  )
        assertThat(marvelCharactersUIState.canRequestNewCharactersPage, `is`(false))
    }
}