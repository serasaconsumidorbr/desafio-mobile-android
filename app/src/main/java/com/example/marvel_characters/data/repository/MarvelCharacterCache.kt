package com.example.marvel_characters.data.repository

import com.example.marvel_characters.data.dto.CharacterDataWrapperDTO
import javax.inject.Inject

class MarvelCharacterCache @Inject constructor() : CharacterCache {
    val cache = mutableMapOf<Int, CharacterDataWrapperDTO>()

    override fun savePage(page: Int, result: CharacterDataWrapperDTO) {
        cache[page] = result
    }

    override fun getPage(page: Int): CharacterDataWrapperDTO? {
        if (cache.containsKey(page)) {
            return cache.getValue(page)
        }
        return null
    }
}