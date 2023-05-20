package br.com.marvelcomics.model

import androidx.annotation.StringRes
import br.com.marvelcomics.base.util.PageDataState

sealed interface MarvelCharacterEntry {
    class FeatureItem(val list: List<MarvelCharacter>) : MarvelCharacterEntry
    class Item(val item: PageDataState<MarvelCharacter>) : MarvelCharacterEntry
    class Title(@StringRes val title: Int) : MarvelCharacterEntry
}