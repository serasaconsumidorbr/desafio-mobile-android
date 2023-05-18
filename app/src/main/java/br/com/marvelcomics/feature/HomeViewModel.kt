package br.com.marvelcomics.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import br.com.marvelcomics.model.MarvelCharacter

class HomeViewModel(
    private val page: Pager<Int, MarvelCharacter>
) : ViewModel() {

    val characters = page.flow.cachedIn(viewModelScope)
}