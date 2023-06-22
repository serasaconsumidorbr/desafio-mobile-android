package com.example.marvel_characters.data.repository

import com.example.marvel_characters.data.dto.CharacterDataWrapperDTO

interface CharacterCache {
    fun savePage(page: Int, result: CharacterDataWrapperDTO)
    fun getPage(page: Int): CharacterDataWrapperDTO?
}