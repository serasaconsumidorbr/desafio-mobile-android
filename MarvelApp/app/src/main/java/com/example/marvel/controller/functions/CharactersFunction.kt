package com.example.marvel.controller.functions

import com.example.marvel.model.Characters

class CharactersFunction {

    fun getElementsList(numShowElementsCard:Int, listItemData: List<Characters>): List<Characters> {

        if (numShowElementsCard < listItemData.size)
            return listItemData.subList(numShowElementsCard, listItemData.size)
        else
            return emptyList()
    }

}