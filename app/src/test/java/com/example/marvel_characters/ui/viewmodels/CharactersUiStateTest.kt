package com.example.marvel_characters.ui.viewmodels

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Test


class CharactersUIStateTest
{
    @Test
    fun shouldReturnCanRequestNewCharactersPageTrue()
    {
        val charactersUIState=CharactersUIState(hasNextPage=true, loading = false  )
        assertThat(charactersUIState.canRequestNewCharactersPage, `is`(true))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenLoading()
    {
        val charactersUIState=CharactersUIState(hasNextPage=true, loading = true  )
        assertThat(charactersUIState.canRequestNewCharactersPage, `is`(false))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenHasNotNextPage()
    {
        val charactersUIState=CharactersUIState(hasNextPage=false, loading = true  )
        assertThat(charactersUIState.canRequestNewCharactersPage, `is`(false))
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseWhenHasNotNextPageAndLoadingAreFalse()
    {
        val charactersUIState=CharactersUIState(hasNextPage=false, loading = false  )
        assertThat(charactersUIState.canRequestNewCharactersPage, `is`(false))
    }
}