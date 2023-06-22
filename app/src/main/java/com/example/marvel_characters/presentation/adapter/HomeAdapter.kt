package com.example.marvel_characters.presentation.adapter

import com.example.marvel_characters.core.adapter.ListAdapterDelegate
import com.example.marvel_characters.presentation.adapter.delegate.CharactersItemDelegate
import com.example.marvel_characters.presentation.adapter.delegate.HeaderItemDelegate
import com.example.marvel_characters.presentation.adapter.delegate.TitleItemDelegate
import com.example.marvel_characters.presentation.model.CharacterListItems

class HomeAdapter : ListAdapterDelegate<CharacterListItems>(
    listOf(
        TitleItemDelegate(),
        HeaderItemDelegate(HeaderCharacterAdapter()),
        TitleItemDelegate(),
        CharactersItemDelegate(CharacterAdapter())
    )
)