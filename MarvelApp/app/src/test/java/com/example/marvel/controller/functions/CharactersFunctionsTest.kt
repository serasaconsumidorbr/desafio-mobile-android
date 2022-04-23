package com.example.marvel.controller.functions

import com.example.marvel.model.Characters
import org.junit.Assert.*

import org.junit.Test

class CharatersFunctionsTest {

    private val listItemData: List<Characters> = mutableListOf(
        Characters("1","Person 1","Description 1","", null),
        Characters("2","Person 2","Description 2","", null),
        Characters("3","Person 3","Description 3","", null),
        Characters("4","Person 4","Description 4","", null),
        Characters("5","Person 5","Description 5","", null),
    )

    @Test
    fun getElementsList() {

        val charactersFunction = CharactersFunction()

        val numShowElementsCard:Int = 3

        assertEquals(listItemData.subList(3,listItemData.size), charactersFunction.getElementsList(numShowElementsCard,listItemData))
    }
}